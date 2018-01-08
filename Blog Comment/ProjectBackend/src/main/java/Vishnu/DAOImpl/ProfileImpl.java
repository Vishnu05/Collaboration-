package Vishnu.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.ProfileDao;
import Vishnu.Model.ProfilePic;

@Repository
@Transactional
public class ProfileImpl implements ProfileDao {
	
	@Autowired
	private SessionFactory sessionf;
	public void saveOrUpdate(ProfilePic profilePic) {
		
		Session session=sessionf.getCurrentSession();
		session.saveOrUpdate(profilePic);
		
	}
	public ProfilePic getprofilepic(String username) {
		
		Session session=sessionf.getCurrentSession();
		ProfilePic profilepic=(ProfilePic)session.get(ProfilePic.class, username);
		return profilepic;
	}

}
