package Vishnu.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.FriendDao;
import Vishnu.Model.Friend;
import Vishnu.Model.User;

@Repository
@Transactional
public class FriendImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionF;

	public List<User> getListOfSuggestedUsers(String username) {
		
		Session session=sessionF.getCurrentSession();
		String queryString="select * from user_table where username in (select username from user_table where username!=? minus (select fromid from Friend where toid=? and status!='D' union select toid from Friend where fromid=? and status!='D'))";
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0,username);
		query.setString(1,username);
		query.setString(2,username);
		query.addEntity(User.class);
		List<User> obj=query.list();
		return obj;
	}

 

	public void addFriendRequest(String username, String toid) {
		Session session=sessionF.getCurrentSession();
		Friend friend=new Friend();
		friend.setFromid(username);
		friend.setToid(toid);
		friend.setStatus('P');
		session.save(friend);		
	}

}
