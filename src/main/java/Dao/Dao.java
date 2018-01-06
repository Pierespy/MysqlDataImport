package Dao;




import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.Vector;

public class Dao extends BaseDao {

    private static final Dao dao;
    

    static {
        dao = new Dao();
    }

    public static Dao getInstance() {
        return dao;
    }

    // 相册
   /* public Vector selectAlbum(int id) {
        return super.selectOnlyNote("select * from tb_album where id=" + id);
    }

    public Vector selectAlbu
    ms(int fatherId) {
        return super.selectSomeNote("select * from tb_album where father_id=" + fatherId);
    }*/
public String getIdByName(String name,  String IdName){
	try{
		Connection conn =JDBC.getConnection();
		String sql="select "+IdName+" from my_order left outer join user on my_order.user_id=user.user_id  where user_name=?";
		System.out.println(name);
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,name);

		return (String) super.selectOnlyValue(pstmt).toString();
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}
}
    public Vector selectUser(String username,String password)
    {	
    	try
    	{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from user where user_name =?and password =?";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		pstmt.setString(1, username);
    		pstmt.setString(2,password);
    		return  super.selectOnlyNote(pstmt);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return null;
		}
    }
	/*public Vector selectProxy()
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="select * from "+PathHelper.getdatabasename();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}*/
    
    //changed
    public boolean insertUser(String username, String password,String phone,String area)
    {
    	try
    	{
    		Connection conn = JDBC.getConnection();
    		String sql="insert into user(user_name,password,tel_num,area) values(?,?,?,?)";
    		PreparedStatement pstmt=conn.prepareStatement(sql);
    		pstmt.setString(1, username);
    		pstmt.setString(2, password);
    		pstmt.setString(3, phone);
    		pstmt.setString(4, area);
    		return  super.longHaul(pstmt,conn);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		return false;
		}
 
    
    }
	public boolean insertreviewData(String productid, String userid,String profilename,String helpfulness,String score,String reviewtime,String summary)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into reviewdata(productid,userid,profilename,helpfulness,score,reviewtime,summary) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productid);
			pstmt.setString(2, userid);
			pstmt.setString(3, profilename);
			pstmt.setString(4, helpfulness);
			pstmt.setString(5, score);
			pstmt.setString(6, reviewtime);
			pstmt.setString(7, summary);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	/*public boolean insertMovieInfo(MovieEntity movieEntity)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into movieinfo(movieid,moviename,director,leadingactors,actors,genres,releasetime,versionurls) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movieEntity.getMovieID());
			pstmt.setString(2, movieEntity.getMovieName());
			pstmt.setString(3, movieEntity.getDirector());
			pstmt.setString(4, movieEntity.getLeadingActors());
			pstmt.setString(5, movieEntity.getActors());
			pstmt.setString(6, movieEntity.getMovieType());
			pstmt.setString(7, movieEntity.getReleaseTime());
            pstmt.setString(8, movieEntity.getVersions());
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}*/
	public Vector selectUncrawledURLs(int count) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT url FROM unclawedURLs WHERE id<5541 LIMIT ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,count);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public Vector selectAllMovieIDinVer()
	{
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT movieid FROM newmovieinfovernum WHERE id>6196";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public Vector selectMovieinfo(int count, int id) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT * FROM movieinfo WHERE id>? LIMIT ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.setInt(2,count);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectAllMovieLeftAD() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT moviename,director,actors,versionurls,movieid from movieinfoAD0104";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectMovieWithSameLeftAD() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT moviename,t_moviename,director,actors,versionurls,t_versionurls,movieid,t_movieid from ((SELECT director,actors, moviename as t_moviename,versionurls as t_versionurls,movieid as t_movieid from movieinfoAD0104)AS T natural join movieinfoAD0104)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
    public Vector selectMovieWithSameAD() {
        try
        {
            Connection conn = JDBC.getConnection();
            //String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
            String sql="SELECT moviename,t_moviename,director,actors,versionurls,t_versionurls,movieid,t_movieid from ((SELECT director,actors, moviename as t_moviename,versionurls as t_versionurls,movieid as t_movieid from movieinfo WHERE director <>''and director <>'Various'and director <>'N/a'and director <>'n/a'and actors<>''and actors<>'Various'and actors <>'N/a'and actors <>'n/a')AS T natural join movieinfo)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            return  super.selectSomeNote(pstmt);
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
	public Vector selectNeedToDeleteURLIDs() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT cid FROM movieinfo NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,unclawedURLs.url,unclawedURLs.id AS cid FROM unclawedURLs) AS T";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectOnlyMovieInfo(String movieid) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT * FROM movieinfo WHERE movieid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,movieid);
			return  super.selectOnlyNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectAllMovieSpider()
	{
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT * FROM movieinfo_spider1225";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectOnlyMovieInfoAD(String movieid) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT * FROM movieinfoAD0104 WHERE movieid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,movieid);
			return  super.selectOnlyNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicateMovieid(String movieid) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT id FROM newmovieinfovernum WHERE movieid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,movieid);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicateMovieidinver(String movieid) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT id FROM movieinfovernum WHERE movieid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,movieid);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicateMovieiinfos(String movieid) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT versionnums FROM movieinfo WHERE movieid=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,movieid);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicateURLsByurl(String url) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT id FROM crawlagainurls WHERE url=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,url);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectMovieWithOutTime() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT movieid from newmovieinfovernum where releasetime=''";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicates() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT movieid FROM (SELECT movieid,count(movieid) as num FROM newmovieinfovernum GROUP BY movieid)as T WHERE num>1";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicatesinVer() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT movieid FROM (SELECT movieid,count(movieid) as num FROM movieinfovernum where id >6196 GROUP BY movieid)as T WHERE num>1";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Vector selectDuplicateurls() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT DISTINCT url FROM (SELECT url,count(url) as num FROM crawlagainurls GROUP BY url)as T WHERE num>1";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeValue(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public boolean deleteCrawledURls(String url) {
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="DELETE FROM unclawedURLs WHERE url=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, url);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean deleteDupicateMovieinfo(int id) {
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="DELETE FROM newmovieinfovernum WHERE id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean deleteCrawledURls(int id)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="DELETE FROM unclawedURLs WHERE id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}

	//B000AZ69DC B001AQJWLG
	public boolean insertMovieInfo(String movieid, String moviename, String director, String leadingactors, String actors, String genres, String releasetime, String vertionurls)
	{
		try
		{
			/*System.out.println(movieid);
			System.out.println(moviename);
			System.out.println(director);
			System.out.println(leadingactors);
			System.out.println(actors);
			System.out.println(genres);
			System.out.println(releasetime);
			System.out.println(vertionurls);*/

			Connection conn = JDBC.getConnection();
			String sql="insert into movieinfo(movieid,moviename,director,leadingactors,actors,genres,releasetime,versionurls) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movieid);
			pstmt.setString(2, moviename);
			pstmt.setString(3, director);
			pstmt.setString(4, leadingactors);
			pstmt.setString(5, actors);
			pstmt.setString(6, genres);
			pstmt.setString(7, releasetime);
			pstmt.setString(8, vertionurls);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean insertMovieInfoVer(String movieid, String moviename, String director, String leadingactors, String actors, String genres, String releasetime, int vertions)
	{
		try
		{

			Connection conn = JDBC.getConnection();
			String sql="insert into newmovieinfovernum(movieid,moviename,director,leadingactors,actors,genres,releasetime,versionnums) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movieid);
			pstmt.setString(2, moviename);
			pstmt.setString(3, director);
			pstmt.setString(4, leadingactors);
			pstmt.setString(5, actors);
			pstmt.setString(6, genres);
			pstmt.setString(7, releasetime);
			pstmt.setInt(8, vertions);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean insertActRecord(String movieid, String actor, String director,  String genres, int ifleading)
	{
		try
		{

			Connection conn = JDBC.getConnection();
			String sql="insert into actrecord (movieid,actor,director,genre,ifleading) values(?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movieid);
			pstmt.setString(2, actor);
			pstmt.setString(3, director);
			pstmt.setString(4, genres);
			pstmt.setInt(5, ifleading);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean insertMovie(String movieid, String moviename, String director,  String genres,int numofversion, int year,int month,int day)
	{
		try
		{

			Connection conn = JDBC.getConnection();
			String sql="insert into movie (movieid,moviename,movietype,genre,numoftypes,year,month,day) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, movieid);
			pstmt.setString(2, moviename);
			pstmt.setString(3, director);
			pstmt.setString(4, genres);
			pstmt.setInt(5, numofversion);
			pstmt.setInt(6, year);
			pstmt.setInt(7, month);
			pstmt.setInt(8, day);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean insertComment(String username,String movieid,  String comment,  int score, int year,int month,int day)
	{
		try
		{

			Connection conn = JDBC.getConnection();
			String sql="insert into comment (username,movieid,comment,score,year,month,day) values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, movieid);
			pstmt.setString(3, comment);
			pstmt.setInt(4, score);
			pstmt.setInt(5, year);
			pstmt.setInt(6, month);
			pstmt.setInt(7, day);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	/*public boolean insertProxy(String ip,int port)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into "+ PathHelper.getdatabasename()+"(ip,port) values(?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setInt(2, port);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}*/
	/*public boolean deleteProxy()
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="DELETE from "+PathHelper.getdatabasename();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}*/
    public boolean insertUncrawledURL(String uncrawledurl)
    {
        try
        {
            Connection conn = JDBC.getConnection();
            String sql="insert into crawlagainurls(url) values(?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, uncrawledurl);
            return  super.longHaul(pstmt,conn);
        }
        catch (Exception e) {
            // TODO: handle exception
            return false;
        }


    }
	public boolean insertUselessUrl(String uncrawledurl)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into uselessURLs(url) values(?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, uncrawledurl);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
	public boolean insertProduct(String productid)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into products_copy7(productid) values(?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, productid);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}


	}
    public Vector getNutritionInfor(String nutrition_id){
    	try
    	{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_nutrition where nutrition_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(nutrition_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
	public Vector getAllProductIds(){
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="select DISTINCT productid from products_copy7";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			return super.selectSomeValue(pstmt);
		}
		catch(Exception e){
			return null;
		}
	}
	/*public Vector getProxyWithIPandPort(String ip,int port){
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="select * from "+PathHelper.getdatabasename()+" where ip=? and port=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,ip);
			pstmt.setInt(2,port);
			return super.selectSomeNote(pstmt);
		}
		catch(Exception e){
			return null;
		}
	}*/
    
    public Vector getReoveryInfor(String recovery_id){
    	try{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_recovery where recovery_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(recovery_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
 
   
    
    public Vector getIllnessInfor(String illness_id){
    	try{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_illness where illness_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(illness_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    
    public Vector getAllergicInfor(String allergic_id){
    	try{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_allergic where allergic_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(allergic_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    
    public Vector getTalentInfor(String tallent_id){
    	try{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_talent where talent_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(tallent_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
    
//    public Vector getSeriousGeneInfor(String serious_gene_id ){
//    	try{
//    		Connection conn = JDBC.getConnection();
//    		String sql="select * from bus_serious_gene where serious_gene_id=? ";
//    		PreparedStatement pstmt = conn.prepareStatement(sql);
//    		pstmt.setInt(1, Integer.parseInt(serious_gene_id));
//    		return super.selectOnlyNote(pstmt);
//    	}
//    	catch(Exception e){
//    		return null;
//    	}
//    }
    
    
    public Vector getGeneInfor(String gene_id ){
    	try{
    		Connection conn = JDBC.getConnection();
    		String sql="select * from bus_gene where gene_id=? ";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, Integer.parseInt(gene_id));
    		return super.selectOnlyNote(pstmt);
    	}
    	catch(Exception e){
    		return null;
    	}
    }
//    public Vector getVigilantGeneInfor(String vigilant_gene_id ){    
//    	try{
//    		Connection conn = JDBC.getConnection();
//    		String sql="select * from bus_vigilant_gene where vigilant_gene_id=? ";
//    		PreparedStatement pstmt = conn.prepareStatement(sql);
//    		pstmt.setInt(1, Integer.parseInt(vigilant_gene_id));
//    		return super.selectOnlyNote(pstmt);
//    	}
//    	catch(Exception e){
//    		return null;
//    	}
//    }
 
    // 照片
    /*public Vector selectPhoto(String photoName) {
        return super.selectOnlyNote("select * from tb_photo where num='" + photoName + "'");
    }*/

   /* public Vector selectPhoto(int albumId, String title, String date,
            char compare, String note) {
        if (date.length() == 0) {
            return selectPhoto(albumId, title, "", note);
        } else {
            return selectPhoto(albumId, title, " date" + compare + "'" + date + "'", note);
        }
    }

    public Vector selectPhoto(int albumId, String title, String startDate,
            String endDate, String note) {
        if (startDate.length() == 0) {
            return selectPhoto(albumId, title, "", note);
        } else {
            return selectPhoto(albumId, title, " date>='" + startDate + "' and date<='" + endDate + "'", note);
        }
    }*/

   /* public Vector selectPhoto(int albumId, String title, String date,
            String note) {
        boolean isSelectAll = true;
        StringBuffer sqlBuffer = new StringBuffer("select * from tb_photo");
        if (albumId != 0) {
            isSelectAll = false;
            sqlBuffer.append(" where album_id in (");
            sqlBuffer.append(albumId);
            for (Iterator it = selectChildAlbumId(albumId).iterator(); it.hasNext();) {
                sqlBuffer.append(",");
                sqlBuffer.append(it.next());
            }
            sqlBuffer.append(")");
        }
        title = title.trim();
        if (title.length() > 0) {
            if (isSelectAll) {
                sqlBuffer.append(" where");
            } else {
                sqlBuffer.append(" and");
            }
            sqlBuffer.append(" title like '%");
            sqlBuffer.append(title.replace(' ', '%'));
            sqlBuffer.append("%'");
        }
        if (date.length() > 0) {
            if (isSelectAll) {
                sqlBuffer.append(" where");
            } else {
                sqlBuffer.append(" and");
            }
            sqlBuffer.append(date);
        }
        note = note.trim();
        if (note.length() > 0) {
            if (isSelectAll) {
                sqlBuffer.append(" where");
            } else {
                sqlBuffer.append(" and");
            }
            sqlBuffer.append(" note like '%");
            sqlBuffer.append(note.replace(' ', '%'));
            sqlBuffer.append("%'");
        }
        return super.selectSomeNote(sqlBuffer.toString());
    }*/
   public Vector selectAllActors() {
	   try
	   {
		   Connection conn = JDBC.getConnection();
		   //String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
		   String sql="SELECT DISTINCT actors from newmovieinfovernum where releasetime!=''";
		   PreparedStatement pstmt=conn.prepareStatement(sql);
		   return  super.selectSomeValue(pstmt);
	   }
	   catch (Exception e) {
		   // TODO: handle exception
		   e.printStackTrace();
		   return null;
	   }
   }
	public Vector selectAllAMovieInfoNew() {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="SELECT * from newmovieinfovernum";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.selectSomeNote(pstmt);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public Long insertMovieinfoForid(String name,int numofversion) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="insert into actrecord(movie_id,actor,director,genre,ifleading,year,month,day) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			return super.longHaulforId(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public boolean insertMovieRecord(int movieid,String director,String actors,String genres,int year,int month,int day) {
		try
		{
			Connection conn = JDBC.getConnection();
			//String sql="SELECT DISTINCT cid FROM movieinfo_copy NATURAL JOIN (SELECT substring_index(url,'/',-1) AS movieid,crawlagainurls_copy.url,crawlagainurls_copy.id AS cid FROM crawlagainurls_copy) AS T";
			String sql="insert into actrecord(movie_id,actor,director,genre,ifleading,year,month,day) values(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			return  super.longHaul(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Long insertUserForId(String username,String password,String phone,String address)
	{
		try
		{
			Connection conn = JDBC.getConnection();
			String sql="insert into user(user_name,password,tel_num,area,identity_num,year_of_birth) values(?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, "meiyoushenfenzheng");
			pstmt.setDate(6, new Date(System.currentTimeMillis()));
			return super.longHaulforId(pstmt,conn);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}


	}
    
}
