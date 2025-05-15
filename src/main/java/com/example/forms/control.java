package com.example.forms;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
public class control {
	@Autowired
	agent a;
	@Autowired
	bagent ba;
	@Autowired
	pagent pa;
	@Autowired
	eagent ea;
	@Autowired
	eauction_agent eab;
	@Autowired
	bauction_agent bab;
	@Autowired
	pauction_agent pab;
	@Autowired
	adminagent adm;
	
	
	@RequestMapping("/gts")
	String one()
	{
		return "home";
	}
	@RequestMapping("/signup")
	String two()
	{
		return "signup";
	}
	//User data storing
	@RequestMapping("/store")
	String three(@RequestParam String name, String phonenumber, String mail, String password, Model m) {
	    users u = new users();
	    u.setName(name);
	    u.setPhonenumber(phonenumber);
	    u.setMail(mail);
	    u.setPassword(password);

	    users savedUser = a.save(u); // Save user to DB

	    savedUser.setMemberid(savedUser.getId()); // ✅ Ensure member ID is assigned
	    a.save(savedUser);  // ✅ Save again with correct member ID

	    m.addAttribute("memberid", savedUser.getMemberid()); // Pass member ID to success page
	    return "success";
	}

	@RequestMapping("/plans")
	String three()
	{
		return "plans";
	}
	@RequestMapping("/login")
	String four()
	{
		return "login";
	}
	@RequestMapping("/gplans")
	String five()
	{
		return "guestplan";
	}
	@RequestMapping("/forgotpassword")
	String six()
	{
		return "forgotpassword";
	}
	@RequestMapping("/terms&conditions")
	String seven()
	{
		return "tc";
	}
	@RequestMapping("/adlogin")
	String eight()
	{
		return "adminlogin";
	}
	@RequestMapping("/addb")
	String nine(Model model)
	{
		List<users> users = (List<users>) a.findAll();  // Get list of users
        List<basic> basicPlans = (List<basic>) ba.findAll();  // Get list of basic plans
        List<premium> premiumPlans = (List<premium>) pa.findAll();  // Get list of premium plans
        List<elite> elitePlans = (List<elite>) ea.findAll();  // Get list of elite plans
        List<elite_auction> eauct = (List<elite_auction>) eab.findAll();  // Get list of elite plans
        List<premium_auction> pauct = (List<premium_auction>) pab.findAll();  // Get list of elite plans
        List<basic_auction> bauct = (List<basic_auction>) bab.findAll();  // Get list of elite plans




        model.addAttribute("users", users);
        model.addAttribute("basicPlans", basicPlans);
        model.addAttribute("premiumPlans", premiumPlans);
        model.addAttribute("elitePlans", elitePlans);
        model.addAttribute("eliteAuctions", eauct);
        model.addAttribute("premiumAuction", pauct);
        model.addAttribute("basicAuction", bauct);
		return "adminmanage";
	}
	@RequestMapping("/bact")
	String ten()
	{
		return "abauction";
	}
	@RequestMapping("/pact")
	String eleven()
	{
		return "apauction";
	}
	@RequestMapping("/eact")
	String twelve()
	{
		return "aeauction";
	}
	@RequestMapping("/aup")
	String thirteen()
	{
		return "aupdate";
	}
	@RequestMapping("/bactwr")
	String fourteen()
	{
        return "bwinner";
    }

	@RequestMapping("/pactwr")
	String fifteen()
	{
        return "pwinner"; // Redirects to bwinner.html
    }
	@RequestMapping("/eactwr")
	String sixteen() 
	{

        return "ewinner"; // Redirects to bwinner.html
	}
	//User login
	@PostMapping("/planslist")
    public String login(
            @RequestParam("memberId") int memberId,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Optional<users> userOpt = a.findByMemberid(memberId);

        if (userOpt.isPresent()) {
            users usero = userOpt.get();
            if (usero.getName().equals(name) && usero.getPhonenumber().equals(phone) && usero.getPassword().equals(password)) {
                session.setAttribute("user", usero); // Store user in session
                return "redirect:/plans"; // Redirect to plans page
            }
        }

        model.addAttribute("error", "Invalid Credentials");
        return "login"; // Stay on login page if credentials are invalid
    }
    @RequestMapping("choose")
    String choose()
    {
    	return "join";
    }
    @RequestMapping("basic")
    String basic()
    {
    	return "basicplan";
    }
    @RequestMapping("premium")
    String premium()
    {
    	return "premiumplan";
    }
    @RequestMapping("elite")
    String elite()
    {
    	return "eliteplan";
    }
    @RequestMapping("bdetails")
    String bdetails()
    {
    	return "basicform";
    }
    @RequestMapping("pdetails")
    String pdetails()
    {
    	return "premiumform";
    }
    @RequestMapping("edetails")
    String edetails()
    {
    	return "eliteform";
    }
    @RequestMapping("bauction")
    String basicauction()
    {
    	return "bauction";
    }
    @RequestMapping("eauction")
    String eliteauction()
    {
    	return "eauction";
    }
    @RequestMapping("pauction")
    String premiumauction()
    {
    	return "pauction";
    }
    @RequestMapping("basicentrys")
    String bentry()
    {
    	return "basicmember";
    }
    @RequestMapping("eliteentrys")
    String eentry()
    {
    	return "elitemember";
    }
    @RequestMapping("premiumentrys")
    String pentry()
    {
    	return "premiummember";
    }
    //Password change
	@RequestMapping("/upass")
	public String updatePassword(@RequestParam int memberid, 
	                             @RequestParam String password, 
	                             Model m) {
	    Optional<users> uppass = a.findByMemberid(memberid);  // ✅ Corrected datatype in repository

	    if (uppass.isPresent()) {
	        users userd = uppass.get();
	        userd.setPassword(password); // ✅ Only update password
	        a.save(userd); // ✅ Save updated password

	        m.addAttribute("message", "Password updated successfully!");
	        return "passuccess";  // ✅ Redirect to success page
	    } else {
	        m.addAttribute("message", "Member ID not found!");
	        return "passerror";  // ✅ Redirect to error page
	    }
	}
	@RequestMapping("/reupdate")
	public String reup(@RequestParam("memberid") int memberid, Model model) {
	    users user = a.findByMemberid(memberid).orElse(null);

	    if (user != null) {
	        model.addAttribute("datas", user);  // ✅ Pass user details to UI
	        model.addAttribute("memberid", user.getMemberid());
	        model.addAttribute("name", user.getName());
	        model.addAttribute("phonenumber", user.getPhonenumber());
	        model.addAttribute("mail", user.getMail());
	        
	        return "resetpassword";  // ✅ Load the reset password page with user details
	    } else {
	        model.addAttribute("message", "Member ID not found!");
	        return "passerror";  // Redirect to error page
	    }
	    
	}
	//Entry form
	@RequestMapping("/basicentry")
	String bentry
	(@RequestParam  String memberid,
    String name,
    String phonenumber,
    String mail,
    String monthjoin,
    MultipartFile ab,
    MultipartFile af,
    MultipartFile pf,
    MultipartFile pb, Model bp)throws IOException {
		  File directory = new File("C:\\Users\\Bashid\\Documents\\workspace-sts\\chitfunds_management_system\\src\\main\\resources\\static\\Proofs");
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	    
	        String uniqueID = UUID.randomUUID().toString();
	        
	        String fileNameab = uniqueID + "_" + ab.getOriginalFilename();
	        File targetFileab = new File(directory, fileNameab);
	        ab.transferTo(targetFileab);	        
	        
 	        String fileNameaf = uniqueID + "_" + af.getOriginalFilename();
	        File targetFileaf = new File(directory, fileNameaf);
	        af.transferTo(targetFileaf);
	        
	        String fileNamepb = uniqueID + "_" + pb.getOriginalFilename();
	        File targetFilepb = new File(directory, fileNamepb);
	        pb.transferTo(targetFilepb);
	        
	        
	        String fileNamepf = uniqueID + "_" + pf.getOriginalFilename();
	        File targetFilepf = new File(directory, fileNamepf);
	        pf.transferTo(targetFilepf);
	        
	        basic bs = ba.findByMemberid(memberid).orElse(new basic());
	    bs.setMemberid(memberid);
	    bs.setName(name);
	    bs.setPhonenumber(phonenumber);
	    bs.setMail(mail);
	    bs.setMonthjoin(monthjoin);
	    bs.setAb("/Proofs/" + fileNameab);
	    bs.setAf("/Proofs/" + fileNameaf);
	    bs.setPb("/Proofs/" + fileNamepb);
	    bs.setPf("/Proofs/" + fileNamepf);
	   
 
	    ba.save(bs);
   
        bp.addAttribute("bdatas", ba.findAll());
	     
    return "basicmember";
	}
	@RequestMapping("/eliteentry")
	String eentry(@RequestParam  String memberid,
    String name,
    String phonenumber,
    String mail,
    String monthjoin,
    MultipartFile ab,
    MultipartFile af,
    MultipartFile pf,
    MultipartFile pb, Model ep)throws IOException {
		  File directory = new File("C:\\Users\\Bashid\\Documents\\workspace-sts\\chitfunds_management_system\\src\\main\\resources\\static\\Proofs");
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	    
	        String uniqueID = UUID.randomUUID().toString();
	        
	        String fileNameab = uniqueID + "_" + ab.getOriginalFilename();
	        File targetFileab = new File(directory, fileNameab);
	        ab.transferTo(targetFileab);	        
	        
 	        String fileNameaf = uniqueID + "_" + af.getOriginalFilename();
	        File targetFileaf = new File(directory, fileNameaf);
	        af.transferTo(targetFileaf);
	        
	        String fileNamepb = uniqueID + "_" + pb.getOriginalFilename();
	        File targetFilepb = new File(directory, fileNamepb);
	        pb.transferTo(targetFilepb);
	        
	        
	        String fileNamepf = uniqueID + "_" + pf.getOriginalFilename();
	        File targetFilepf = new File(directory, fileNamepf);
	        pf.transferTo(targetFilepf);

	        elite es = ea.findByMemberid(memberid).orElse(new elite());
	    es.setMemberid(memberid);
	    es.setName(name);
	    es.setPhonenumber(phonenumber);
	    es.setMail(mail);
	    es.setMonthjoin(monthjoin);
	    es.setAb("/Proofs/" + fileNameab);
	    es.setAf("/Proofs/" + fileNameaf);
	    es.setPb("/Proofs/" + fileNamepb);
	    es.setPf("/Proofs/" + fileNamepf);
	   
 
	    ea.save(es);
   
        ep.addAttribute("edatas", ea.findAll());
	     
    return "elitemember";
	}
	@RequestMapping("/premiumentry")
	String pentry(@RequestParam  String memberid,
    String name,
    String phonenumber,
    String mail,
    String monthjoin,
    MultipartFile ab,
    MultipartFile af,
    MultipartFile pf,
    MultipartFile pb, Model pp)throws IOException {
		  File directory = new File("C:\\Users\\Bashid\\Documents\\workspace-sts\\chitfunds_management_system\\src\\main\\resources\\static\\Proofs");
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	    
	        String uniqueID = UUID.randomUUID().toString();
	        
	        String fileNameab = uniqueID + "_" + ab.getOriginalFilename();
	        File targetFileab = new File(directory, fileNameab);
	        ab.transferTo(targetFileab);	        
	        
 	        String fileNameaf = uniqueID + "_" + af.getOriginalFilename();
	        File targetFileaf = new File(directory, fileNameaf);
	        af.transferTo(targetFileaf);
	        
	        String fileNamepb = uniqueID + "_" + pb.getOriginalFilename();
	        File targetFilepb = new File(directory, fileNamepb);
	        pb.transferTo(targetFilepb);
	        
	        
	        String fileNamepf = uniqueID + "_" + pf.getOriginalFilename();
	        File targetFilepf = new File(directory, fileNamepf);
	        pf.transferTo(targetFilepf);

	        premium ps = pa.findByMemberid(memberid).orElse(new premium());
	    ps.setMemberid(memberid);
	    ps.setName(name);
	    ps.setPhonenumber(phonenumber);
	    ps.setMail(mail);
	    ps.setMonthjoin(monthjoin);
	    ps.setAb("/Proofs/" + fileNameab);
	    ps.setAf("/Proofs/" + fileNameaf);
	    ps.setPb("/Proofs/" + fileNamepb);
	    ps.setPf("/Proofs/" + fileNamepf);
	   
 
	    pa.save(ps);
   
        pp.addAttribute("pdatas", pa.findAll());
	     
    return "premiummember";
	}
	//Auction Bidding
	@RequestMapping("/basicsubmitbid")
    String basicauctionsubmit(@RequestParam String memberid,
            @RequestParam String name,
           @RequestParam String bidamount,Model bd) {

   
       basic_auction bba = new basic_auction();
       bba.setMemberid(memberid);
       bba.setName(name);
       bba.setBidamount(bidamount);
        
 

       // Save the object using the 'agent' repository/service
       bab.save(bba);
       List<basic_auction> baucdatas=(List<basic_auction>)bab.findAll();
       bd.addAttribute("bauctiondatas", baucdatas);

    
    	return "bbidmembers";
    }
	@RequestMapping("/elitesubmitbid")
    String eliteauctionsubmit(@RequestParam String memberid,
            @RequestParam String name,
           @RequestParam String bidamount,Model ed) {

   
       elite_auction eba = new elite_auction();
       eba.setMemberid(memberid);
       eba.setName(name);
       eba.setBidamount(bidamount);
        
 

       // Save the object using the 'agent' repository/service
      eab.save(eba);
       List<elite_auction> eaucdatas=(List<elite_auction>)eab.findAll();
       ed.addAttribute("eauctiondatas", eaucdatas);

    
    	return "ebidmembers";
    }
    @RequestMapping("/premiumsubmitbid")
    String premiumauctionsubmit(@RequestParam String memberid,
            @RequestParam String name,
           @RequestParam String bidamount,Model pd) {

   
       premium_auction pba = new premium_auction();
       pba.setMemberid(memberid);
       pba.setName(name);
       pba.setBidamount(bidamount);
        
 

       // Save the object using the 'agent' repository/service
      pab.save(pba);
       List<premium_auction> paucdatas=(List<premium_auction>)pab.findAll();
       pd.addAttribute("pauctiondatas", paucdatas);

    
    	return "pbidmembers";
    }
    //Admin
    @PostMapping("/adcheck")
    public String adlogin(
    		 @RequestParam("adminid") String adminid,
             @RequestParam("adminname") String adminname,
 
             @RequestParam("adminpassword") String adminpassword,
             @RequestParam(value = "tab", defaultValue = "users") String selectedTab,
            HttpSession session,
            Model model) {

        Optional<adminlogin> adminOpt = adm.findByAdminid(adminid);

        if (adminOpt.isPresent()) {
            adminlogin admin = adminOpt.get();
            if (admin.getAdminname().equals(adminname) && admin.getAdminpassword().equals(adminpassword)) {
            	 List<users> users = (List<users>) a.findAll();  // Get list of users
                 List<basic> basicPlans = (List<basic>) ba.findAll();  // Get list of basic plans
                 List<premium> premiumPlans = (List<premium>) pa.findAll();  // Get list of premium plans
                 List<elite> elitePlans = (List<elite>) ea.findAll();  // Get list of elite plans
                 List<elite_auction> eauct = (List<elite_auction>) eab.findAll();  // Get list of elite plans
                 List<premium_auction> pauct = (List<premium_auction>) pab.findAll();  // Get list of elite plans
                 List<basic_auction> bauct = (List<basic_auction>) bab.findAll();  // Get list of elite plans




                 model.addAttribute("users", users);
                 model.addAttribute("basicPlans", basicPlans);
                 model.addAttribute("premiumPlans", premiumPlans);
                 model.addAttribute("elitePlans", elitePlans);
                 model.addAttribute("eliteAuctions", eauct);
                 model.addAttribute("premiumAuction", pauct);
                 model.addAttribute("basicAuction", bauct);
                 model.addAttribute("selectedTab", selectedTab);
                return "adminmanage"; // Redirect to dashboard after successful login
            }
        }


        model.addAttribute("error", "Invalid Credentials");
        return "adminlogin"; // Stay on login page if credentials are invalid
    }
    @GetMapping("/admin")
    public String showDashboard(Model model, @RequestParam(value = "tab", defaultValue = "users") String selectedTab) {
        List<users> users = (List<users>) a.findAll();  // Get list of users
        List<basic> basicPlans = (List<basic>) ba.findAll();  // Get list of basic plans
        List<elite> elitePlans = (List<elite>) ea.findAll();  // Get list of premium plans
        List<premium> premiumPlans = (List<premium>) pa.findAll();  // Get list of elite plans
        List<elite_auction> eauct = (List<elite_auction>) eab.findAll();  // Get list of elite plans
        List<premium_auction> pauct = (List<premium_auction>) pab.findAll();  // Get list of elite plans
        List<basic_auction> bauct = (List<basic_auction>) bab.findAll();  // Get list of elite plans
        



        model.addAttribute("users", users);
        model.addAttribute("basicPlans", basicPlans);
        model.addAttribute("premiumPlans", premiumPlans);
        model.addAttribute("elitePlans", elitePlans);
        model.addAttribute("eliteAuctions", eauct);
        model.addAttribute("premiumAuction", pauct);
        model.addAttribute("basicAuction", bauct);
        model.addAttribute("selectedTab", selectedTab);

        return "adminmanage";  // Return the correct view name
    }
 
    
    //Update
    @GetMapping("reupdate/{id}")
    String reups(@PathVariable("id") String memberid, Model g) {
        basic bup = ba.findByMemberid(memberid).orElse(null);
        g.addAttribute("bdatas", bup != null ? List.of(bup) : Collections.emptyList());
        // Add the months list
        List<String> months = List.of(
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        );
        g.addAttribute("months", months);
        return "aupdate";
    }

    @GetMapping("reupdates/{id}")
    String reupss(@PathVariable("id") String memberid, Model g) {
        premium pup = pa.findByMemberid(memberid).orElse(null);
        g.addAttribute("pdatas", pup != null ? List.of(pup) : Collections.emptyList());
        // Add the months list
        List<String> months = List.of(
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        );
        g.addAttribute("months", months);
        return "aupdate";
    }

    @GetMapping("reupdatess/{id}")
    String reupsss(@PathVariable("id") String memberid, Model g) {
        elite eup = ea.findByMemberid(memberid).orElse(null);
        g.addAttribute("edatas", eup != null ? List.of(eup) : Collections.emptyList());
        // Add the months list
        List<String> months = List.of(
            "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        );
        g.addAttribute("months", months);
        return "aupdate";
    }


	
    @PostMapping("brupdates")
    String upsp(@RequestParam String memberid,
                @RequestParam String name,
                @RequestParam String phonenumber,
                @RequestParam String mail,
                @RequestParam String monthjoin, 
                Model m) {

        basic bs = ba.findByMemberid(memberid).orElse(null);
        
        if (bs != null) { // Ensure record exists
            bs.setName(name);
            bs.setPhonenumber(phonenumber);
            bs.setMail(mail);
            bs.setMonthjoin(monthjoin);
            
            ba.save(bs); // Save the updated object
        }

        return "redirect:/admin";
    }

	  
	   @PostMapping("prupdates")
	   String upsps(@RequestParam String memberid,
	   @RequestParam String name, 
	   @RequestParam String phonenumber, 
	   @RequestParam String mail,
	   @RequestParam String monthjoin, 
	   Model m) {
	   
	   
	   premium ps=pa.findByMemberid(memberid).orElse(null); 
       if (ps != null) { // Ensure record exists

	   ps.setName(name);
	   ps.setPhonenumber(phonenumber);
	   ps.setMail(mail); 
	   ps.setMonthjoin(monthjoin);
	   
	   
	   // Save the object using the 'agent' repository/service
	   pa.save(ps);
       }
	   
		  return "redirect:/admin"; // Ensure a corresponding 'two.html' exists
	   }
	   
	   @PostMapping("erupdates")
	   String upspss(@RequestParam String memberid,
			   @RequestParam String name, 
			   @RequestParam String phonenumber, 
			   @RequestParam String mail,
			   @RequestParam String monthjoin, 
			   Model m) {
	   
	   
	   elite es=ea.findByMemberid(memberid).orElse(null);
       if (es != null) { // Ensure record exists

	   es.setName(name);
	   es.setPhonenumber(phonenumber); 
	   es.setMail(mail);
	   es.setMonthjoin(monthjoin);
       }
	   
	   
	   // Save the object using the 'agent' repository/service 
	   ea.save(es);
	   
		  return "redirect:/admin"; // Ensure a corresponding 'two.html' exists
	   }
	   //Delete
	   @GetMapping("bdelete/{id}")
	    String bddelte(@PathVariable("id") String memberid, Model g) {
	        basic bup = ba.findByMemberid(memberid).orElse(null);
	        g.addAttribute("bdatas", bup != null ? List.of(bup) : Collections.emptyList());
	        
	        return "edelete";
	    }

	    @GetMapping("pdelete/{id}")
	    String pddelete(@PathVariable("id") String memberid, Model g) {
	        premium pup = pa.findByMemberid(memberid).orElse(null);
	        g.addAttribute("pdatas", pup != null ? List.of(pup) : Collections.emptyList());
	       
	        return "edelete";
	    }

	    @GetMapping("edelete/{id}")
	    String eddelete(@PathVariable("id") String memberid, Model g) {
	        elite eup = ea.findByMemberid(memberid).orElse(null);
	        g.addAttribute("edatas", eup != null ? List.of(eup) : Collections.emptyList());
	         
	        return "edelete";
	    }
	    @PostMapping("basicdeleteMember")
	    String bdeleteMember(@RequestParam int id) {
 
	        if (ba.findById(id).isPresent()) {  // Check if member exists
	            ba.deleteById(id);
 	        } else {
 	        }

	        return "redirect:/admin";
	    }

	    @PostMapping("elitedeleteMember")
	    String edeleteMember(@RequestParam int id) {
 
	    	if (ea.findById(id).isPresent()) { 
	    		// Check if member exists
	            ea.deleteById(id);
 	        } 
	    	else
	    	{
 	        	
 	        }
	        return "redirect:/admin"; // Redirect to updated members list
	    }
	    @PostMapping("premiumdeleteMember")
	    String pdeleteMember(@RequestParam int id) {
 
	    	if (pa.findById(id).isPresent()) {  // Check if member exists
	            pa.deleteById(id);
 	        } else {
 	        }
	        return "redirect:/admin"; // Redirect to updated members list
	    }
	    @GetMapping("/abauction")
	    String abadmauct(@RequestParam(required = false) String memberid,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String bidamount,
	    		Model bd)
	    {
	    	 List<basic_auction> baucdatas=(List<basic_auction>)bab.findAll();
	         bd.addAttribute("bauctiondatas", baucdatas);

	    	return "abauction";
	    }
	    @GetMapping("/apauction")
	    String apadmauct(@RequestParam(required = false) String memberid,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String bidamount,
	    		Model pd)
	    {
	    	List<premium_auction> paucdatas=(List<premium_auction>)pab.findAll();
	        pd.addAttribute("pauctiondatas", paucdatas);
	    	return "apauction";
	    }
	    @GetMapping("/aeauction")
	    String aeadmauct(@RequestParam(required = false) String memberid,
                @RequestParam(required = false) String name,
                @RequestParam(required = false) String bidamount,
	    		Model ed)
	    {
	    	List<elite_auction> eaucdatas=(List<elite_auction>)eab.findAll();
	        ed.addAttribute("eauctiondatas", eaucdatas);

	          
	    	return "aeauction";
	    }
	    //Auction winner
	    @GetMapping("/bwinner")
	    public String bshowWinner(@RequestParam("id") String winnerId, Model model) {
	        List<basic_auction> baucdatas = (List<basic_auction>) bab.findAll();
	        model.addAttribute("bauctiondatas", baucdatas);
	        model.addAttribute("winnerId", winnerId.trim()); // Pass the winner ID to Thymeleaf

	        return "bwinner";
	    }

	    @GetMapping("/pwinner")
	    public String pshowWinnerPage(@RequestParam("id") String winnerId, Model model) {
	        // Fetch existing auction data
	        List<premium_auction> paucdatas = (List<premium_auction>) pab.findAll();
	        
	        model.addAttribute("pauctiondatas", paucdatas); // Pass auction data
	        model.addAttribute("winnerId", winnerId.trim()); // Pass selected winner ID

	        return "pwinner"; // Redirects to bwinner.html
	    }
	    @GetMapping("/ewinner")
	    public String eshowWinnerPage(@RequestParam("id") String winnerId, Model model) {
	        // Fetch existing auction data
	        List<elite_auction> eaucdatas = (List<elite_auction>) eab.findAll();
	        
	        model.addAttribute("eauctiondatas", eaucdatas); // Pass auction data
	        model.addAttribute("winnerId", winnerId.trim()); // Pass selected winner ID

	        return "ewinner"; // Redirects to bwinner.html
	    }
	    
	    

	  

}
	 

	

	

	  

