package com.abscence.core.web;

import java.text.ParseException;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.abscence.core.bo.Absence;
import com.abscence.core.bo.Conversation;
import com.abscence.core.bo.Etudiant;
import com.abscence.core.bo.Inscription;
import com.abscence.core.bo.Message;
import com.abscence.core.bo.Niveau;
import com.abscence.core.bo.Notification;
import com.abscence.core.bo.TypeSeance;
import com.abscence.core.services.IAbsenceService;
import com.abscence.core.services.IConversationService;
import com.abscence.core.services.IEnseignantService;
import com.abscence.core.services.IEtudiantService;
import com.abscence.core.services.IInscriptionService;
import com.abscence.core.services.IMessageService;
import com.abscence.core.services.INiveauService;
import com.abscence.core.services.INotificationService;
import com.abscence.core.services.ITypeSeanceService;
import com.abscence.core.services.IUtilisateurService;
import com.abscence.genericdao.EntityNotFoundException;

@Controller
public class AbsenceController {
	
	@Autowired
	private ITypeSeanceService typeSeanceService;
	
	@Autowired
	private IInscriptionService inscriptionService;
	
	@Autowired 
	private IEnseignantService enseignantService;
	
	@Autowired
	private INiveauService niveauService;
	
	@Autowired
	private IEtudiantService etudiantService;
	
	@Autowired
	private IUtilisateurService utilisateurService;
	
	@Autowired
	private IAbsenceService absenceService;
	
	@Autowired
	private IConversationService conversationService;
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private INotificationService notificationService;
	
	private Integer annee = Calendar.getInstance().get(Calendar.YEAR);
	
	private Integer Etat = 1;
	
	private String typeSaisie = "manuelle";
	
	private long  seuille = 3 ; 
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		
		//model.addAttribute("absenceModel", new Absence());
		
		//model.addAttribute("typeSeanceModel", new TypeSeance());
		model.addAttribute("typeSeanceList", typeSeanceService.getAllTypeSeance());
		
		//model.addAttribute("niveauModel", new Niveau());
		model.addAttribute("niveauList",niveauService.getAllNiveau());
		
		return "formulaireSaisieAbsence";
		
	}

	@PostMapping("/listEtudiant")
	public String ListEtudiant( Model model, @RequestParam("seance") String seance, @RequestParam("niveau") String niveau ,
			@RequestParam("dateDebut") String DateDebut, @RequestParam("dateFin") String DateFin ,WebRequest request ) throws ParseException {
		
		if(seance.equals("") || niveau.equals("") 
				|| DateDebut.equals("") || DateFin.equals("")) {
			return "erreur" ;
		}
		
		
				Date fin = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(DateFin);
				Date debut =new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(DateDebut);
			
		
		
		request.setAttribute("seance", seance,  WebRequest.SCOPE_SESSION);
		request.setAttribute("niveau", niveau,  WebRequest.SCOPE_SESSION);
		request.setAttribute("dateDebut", debut,  WebRequest.SCOPE_SESSION);
		request.setAttribute("dateFin", fin,  WebRequest.SCOPE_SESSION);
		
		
		//model.addAttribute("etudiantList", inscriptionService.getListEtudiantByYear(annee, niveau));
		
		Niveau ni = niveauService.getNiveau(Integer.parseInt(niveau));
		List <Inscription> insNiveau = (List<Inscription>) ni.getInscription();
		List<Inscription> insAnnee = inscriptionService.getListInscriptionByYear(annee);
		
		//List<Inscription> result = (List<Inscription>) insNiveau.stream().distinct().filter(insAnnee::contains).collect(Collectors.toSet());
		List<Etudiant> et = new ArrayList<Etudiant>();
		
		insAnnee.retainAll(insNiveau); 
		
		for(int i = 0 ; i < insAnnee.size(); i++) {
			et.add(insAnnee.get(i).getEtudiant());
		}
		model.addAttribute("etudiantList", et);
			 
		
		return "listEtudiant";
	}
	
	
	@PostMapping("/ajouterAbsence")
	public String AjouterAbsence(@RequestParam("absent") String[] absent , 
			WebRequest request) {
		String seance = (String) request.getAttribute("seance", WebRequest.SCOPE_SESSION);
		String niveau = (String) request.getAttribute("niveau", WebRequest.SCOPE_SESSION);
		Date dateDebut = (Date) request.getAttribute("dateDebut", WebRequest.SCOPE_SESSION);
		Date dateFin = (Date) request.getAttribute("dateFin", WebRequest.SCOPE_SESSION);
		
		Absence ab = new Absence();
		ab.setDateHeureDebutAbsence(dateDebut);
		ab.setDateHeureFinAbsence(dateFin);
		ab.setEtat(Etat);
		ab.setTypeSaisie(typeSaisie);
		
		TypeSeance typeSeance = typeSeanceService.getTypeSeance(Integer.parseInt(seance));
		typeSeance.addAbsences(ab);
		
		for(int i = 0 ; i < absent.length ; i++) {
		
			Integer id = Integer.parseInt(absent[i]);
			Etudiant et = etudiantService.ObtenirEtudiant(id);
			List<Inscription> l =  (List<Inscription>) et.getInscriptions();
			for(int j = 0 ; j < l.size(); j++) {
				
				ab.setInscription(l.get(i));
				ab.setTypeSeance(typeSeance);
				absenceService.addAbsence(ab);
			}
			
		}
		
		
		return "index";
	}
	
	
	@RequestMapping("/rechercheEtudiant")
	public String RechercheEtudiant(Model model, @RequestParam("recherche") String identifiant)		
	{
		try {
			Integer id = Integer.parseInt(identifiant);
			Etudiant et = etudiantService.ObtenirEtudiant(id);
			model.addAttribute("Etudiant", et);
			
			return "AfficheEtudiant" ;
			
		}catch(EntityNotFoundException e) {
			return "erreur";
		}
			
	}
	
	@RequestMapping("/AllerRecherche")
	public String pageRecherche() {
		return "RechercheEtudiant";
	}
	
	
	@RequestMapping(value="/obtenirAbsence/{idUtilisateur}")
	public String ObtenirAbsence(@PathVariable int  idUtilisateur , Model model ) {
		Integer id = Integer.valueOf(idUtilisateur);
		Etudiant et = etudiantService.ObtenirEtudiant(id);
		
		
		// à rectifier car nous pouvons avoir la liste des inscriptions à partir de l'etudiant
		List<Inscription> ins = (List<Inscription>) et.getInscriptions();
		
		
		List <Absence> abs0 = new ArrayList<Absence>();
		for(int i = 0 ; i < ins.size(); i++) {
			Integer idInscription = ins.get(i).getIdInscription();
			
			List<Absence> abs1 = (List<Absence>) ins.get(i).getAbsences();
			for(int j = 0 ; j < abs1.size(); j++) {
				abs0.add(abs1.get(j));
			}
		}
		
		model.addAttribute("ListAbsence", abs0);
		return "listAbsence";
	}
	
	
	@RequestMapping(value="/supprimerAbsence/{idAbsence}")
	public String supprimerAbsence(@PathVariable int idAbsence ) throws ParseException{
		
		Integer id = Integer.valueOf(idAbsence);
		
		Absence abs = absenceService.getAbsence(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateFin = abs.getDateHeureFinAbsence();
		
		String dateActuelleS = sdf.format(new Date());
		
		Date dateActuelle = sdf.parse(dateActuelleS);
		
		long diff = dateActuelle.getTime() - dateFin.getTime();
		
		TimeUnit time = TimeUnit.DAYS;
		
		long difference = time.convert(diff, TimeUnit.MILLISECONDS);
		
		if(difference < seuille ) {
			absenceService.deleteAbsence(id);
		}
		else {
			return "Seuil";
		}
		return "index";
	}
	
	
	@RequestMapping("/DemandeAutorisation")
	public String DemandeAutorisation(Model model) {
		String Demande = "Demande Autorisation Absence";
		List <Conversation> conv = conversationService.getConversationByType("Conversation",
				"type", Demande);
		List<Message> msg = new ArrayList<Message>() ;
		for(int i = 0 ; i < conv.size(); i++) {
			msg.addAll(conv.get(i).getMessages());
		}
		
		model.addAttribute("ListMessage" , msg);
		return "Message";
	}
	
	
	
	@RequestMapping("/reponse/{idReponse}/{idMessage}")
	public String reponseDemande(@PathVariable int idReponse, @PathVariable int idMessage) {
		Integer idRep = Integer.valueOf(idReponse);
		Integer idMsg = Integer.valueOf(idMessage);
		Notification ntf = new Notification();
		ntf.setDateCreation(new Date());
		ntf.setType("Reponse Demande Autorisation");
		ntf.setEtat(1);
		if(idReponse == 1) {
			ntf.setTitre(" Autorisation d absence");
			ntf.setTexte("Vous pouvez vous absenter");
		}
		else {
			ntf.setTitre(" Rejet  d absence");
			ntf.setTexte("Desole mais vous ne pouvez pas vous absenter");
		}
		notificationService.addNotification(ntf);
		messageService.DeleteMessage(idMsg);
		return "index";
	}
	
	
	
	@RequestMapping("/test")
	public String index(Model model) {
		
		Conversation conv1 = new Conversation();
		conv1.setTitre("Autorisation");
		conv1.setType("Demande Autorisation Absence");
		conv1.setEtat(1);
		
		Message msg1 = new Message();
		msg1.setTexte(" Je voulez vous informer que la prochaine seance j ai un rendez vous avec le medecin"
				+ "je ne pourrez pas assiter à la seance ");
		msg1.setDateHeure(new Date());
		
		Message msg2 = new Message();
		msg2.setTexte("J ai un concour que je doit passer donc je ne peut pas assister");
		msg2.setDateHeure(new Date());
		
		conv1.addMessages(msg1);
		conv1.addMessages(msg2);
		
		conversationService.addConversation(conv1);
		
		
		
		/* 
		Inscription ins1 = new Inscription();
		
		ins1.setAnnee(2021);
		ins1.setEtat(1);
		
		Inscription ins2 = new Inscription();
		
		ins2.setAnnee(2021);
		ins2.setEtat(1);
		
//		HashSet<Inscription> hins1 = new HashSet<Inscription>();
//		hins1.add(ins1);
//		
//		HashSet<Inscription> hins2 = new HashSet<Inscription>();
//		hins1.add(ins2);
		
		Niveau ni1 = new Niveau();
		
		ni1.setAlias("GI1");
		ni1.setTitre("Genie Informatique 1");
		
		ni1.addInscription(ins1);
		ni1.addInscription(ins2);
		
		
		Etudiant et = new Etudiant();
		et.setNom("nom1");
		et.setPrenom("prenom1");
		et.setCin("cin1");
		et.setEmail("email@email.com");
		et.setTelephone("012345678");
		et.setPrenomArabe("prenomArabe");
		et.setNomArabe("nomArabe");
		et.setCne("cne1");
		et.setDateNaissance(new Date());
		et.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnW2zxvUPji9bRP94RzkFANggsIrjNd7mxzA&usqp=CAU");
		
		
		Etudiant et2 = new Etudiant();
		
		et2.setNom("nom2");
		et2.setPrenom("prenom2");
		et2.setCin("cin2");
		et2.setEmail("email2@email.com");
		et2.setTelephone("012345678");
		et2.setPrenomArabe("prenomArabe2");
		et2.setNomArabe("nomArabe2");
		et2.setCne("cne2");
		et2.setDateNaissance(new Date());
		et2.setPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtccTeMhfR97vVdZ6MRyay2TCZ0acXhqrvaQ&usqp=CAU");
		
		et.addInscriptions(ins1);
		et2.addInscriptions(ins2);
		
		niveauService.create(ni1);
		etudiantService.create(et);
		etudiantService.create(et2);
		*/
		
		
		return "/index";
	}
}
