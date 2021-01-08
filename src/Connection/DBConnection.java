package Connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Model.BestOF100Playes;
import Model.Country;
import Model.CreateYourOwnMatchWithFriends;
import Model.FriendOf;
import Model.Gift;
import Model.GiftPurchase;
import Model.GiftRank;
import Model.GiftRankPlayer;
import Model.Invited;
import Model.PlayedIn;
import Model.Player;
import Model.Purchase;
import Model.Ranks;

public class DBConnection {

	
	public static final String connectionURL="jdbc:sqlserver://MUTLAQ-PC:1433;databaseName=OnlineGames;integratedSecurity=true;";
	

	
	
	public static void writeToFile(ArrayList<GiftRankPlayer> al) {
		String fileName = "output.txt";
		BufferedWriter writer = null;
		ArrayList<GiftRankPlayer> inserted = new ArrayList<GiftRankPlayer>();
		ArrayList<GiftRankPlayer> updated = new ArrayList<GiftRankPlayer>();
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for(GiftRankPlayer g : al) {
				if(checkRankplayer(g.getRankID(), g.getPlayerID())) {
					inserted.add(g);
				}else {
					updated.add(g);
				}
			}
			writer.write("inserted rows : \n");
			for(GiftRankPlayer g : inserted ) {
				writer.write("rank id = "+g.getRankID()+", player id = "+g.getPlayerID()+"\n");
			}
			writer.write("updated rows : \n");
			for(GiftRankPlayer g : updated ) {
				writer.write("rank id = "+g.getRankID()+", player id = "+g.getPlayerID()+"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static ArrayList<GiftRankPlayer> readRanksPlayers(){
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<GiftRankPlayer> toReturn = new ArrayList<GiftRankPlayer>();
		String path ="RanksPlayers.txt";
		File file = new File(path);
		Scanner scanner =null;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				temp.add(new String(scanner.nextLine()));
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();	
			}finally {
				if(scanner!=null) {
					scanner.close();
				}
			}
		
		for(String s : temp) {
			String [] arr = s.split(",");
			toReturn.add(new GiftRankPlayer(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), java.sql.Date.valueOf(arr[3]), Boolean.parseBoolean(arr[4])));
		}
		
		return toReturn;
	}
	public static boolean checkRankplayer(int rankID, int playerID) {
		Connection con = null;
		String record = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call checkRankPlayer(?,?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1,rankID);
			cs.setInt(2,playerID);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				
				int i=1;
				record=rs.getString(i++);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}

		if(record.equals("true")) {
			return true;
		}
		return false;
	}
	
	public static void  insertGiftRankPlayer(GiftRankPlayer o){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call insertRankPlayerGift(?,?,?,?,?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, o.getRankID());
			cs.setInt(2, o.getPlayerID());
			cs.setInt(3, o.getGiftID());
			if(o.getDateRecieve()!=null) {
				cs.setDate(4, o.getDateRecieve());
			}else {
			     long millis=System.currentTimeMillis();  
			    java.sql.Date date=new java.sql.Date(millis);  
				cs.setDate(4,date );
			}
			cs.setBoolean(5,o.isSuspended());
			ResultSet rs = cs.executeQuery();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static void  updateGiftRankPlayer(GiftRankPlayer o){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call updateRankPlayerGift(?,?,?,?,?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, o.getRankID());
			cs.setInt(2, o.getPlayerID());
			cs.setInt(3, o.getGiftID());
			if(o.getDateRecieve()!=null) {
				cs.setDate(4, o.getDateRecieve());
			}else {
			     long millis=System.currentTimeMillis();  
			    java.sql.Date date=new java.sql.Date(millis);  
				cs.setDate(4,date );
			}
			cs.setBoolean(5,o.isSuspended());
			ResultSet rs = cs.executeQuery();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static ArrayList<Object>  getTable(String tableName){
		ArrayList <Object> toReturn = new ArrayList<Object>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call SelectAll(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setString(1,tableName);
			ResultSet rs = cs.executeQuery();
			if(tableName.equals("BestOf100Players")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new BestOF100Playes(rs.getLong(i++), rs.getTimestamp(i++),  rs.getTimestamp(i++)));
				}
			}else if(tableName.equals("Country")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Country(rs.getInt(i++),rs.getString(i++)));
				}
			}else if(tableName.equals("CreateYourOwnMatchWithFriends")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new CreateYourOwnMatchWithFriends(rs.getLong(i++), rs.getTimestamp(i++),  rs.getTimestamp(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++)));
				}
			}else if(tableName.equals("FriendOf")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new FriendOf(rs.getInt(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("Gift")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Gift(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("GiftPurchase")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new GiftPurchase(rs.getInt(i++), rs.getInt(i++), rs.getTimestamp(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("GiftRank")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new GiftRank(rs.getInt(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("GiftRankPlayer")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new GiftRankPlayer(rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getDate(i++), rs.getBoolean(i++)));	
					}
			}else if(tableName.equals("Invited")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Invited(rs.getInt(i++), rs.getInt(i++), rs.getTimestamp(i++), rs.getTimestamp(i++), rs.getInt(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("PlayedIn")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new PlayedIn(rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++)));	
					}
			}else if(tableName.equals("Players")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Player(rs.getInt(i++), rs.getString(i++), rs.getString(i++),  rs.getDate(i++), rs.getDate(i++), rs.getString(i++),  rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++),rs.getString(i++)));	
					}
			}else if(tableName.equals("Purchase")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Purchase(rs.getInt(i++), rs.getTimestamp(i++)));	
					}
			}else if(tableName.equals("Ranks")) {
				while(rs.next()) {
					int i=1;
					toReturn.add(new Ranks(rs.getInt(i++), rs.getString(i++), rs.getInt(i++)));	
					}
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static ArrayList<String>  getQuery1(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query1()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+= rs.getString(i++)+" ";
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	
	
	public static ArrayList<String>  getQuery2(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query2()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+=rs.getInt(i++)+", ";
				record+=rs.getInt(i++);
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	
	
	public static ArrayList<String>  getQuery3(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query3()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+= rs.getString(i++)+" ";
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static ArrayList<String>  getQuery4(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query4()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+=rs.getString(i++)+", ";
				record+=rs.getInt(i++)+", ";
				record+=rs.getTimestamp(i++)+", ";
				record+=rs.getTimestamp(i++);
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static ArrayList<String>  getQuery5(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query5()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+=rs.getInt(i++)+", ";
				record+=rs.getString(i++)+", ";
				record+=rs.getInt(i++)+", ";
				record+=rs.getString(i++);
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static ArrayList<String>  getQuery6(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query6()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+=rs.getInt(i++);
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static ArrayList<String>  getQuery7(){
		ArrayList <String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call Query7()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				record += rs.getInt(i++)+", ";
				record+= rs.getString(i++)+", ";
				record+=rs.getString(i++);
				toReturn.add(record);			
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static boolean  createNewMatchWithFriends(CreateYourOwnMatchWithFriends newMatch){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call NewMatchWithFriends(?,?,?,?,?,?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setLong(1, newMatch.getGameID());
			cs.setTimestamp(2, newMatch.getGameStartDateTime());
			
			if(newMatch.getGameEndDateTime()!=null) {
				cs.setTimestamp(3, newMatch.getGameEndDateTime());
			}else {
				cs.setNull(3, java.sql.Types.TIMESTAMP);
			}
			cs.setInt(4, newMatch.getPlayerID());
			if(newMatch.getPoints()>=0) {
				cs.setInt(5, newMatch.getPoints());
			}else {
				cs.setNull(5,java.sql.Types.INTEGER );
			}
			if(newMatch.getTimesDied()>=0) {
				cs.setInt(6, newMatch.getTimesDied());
			}else {
				cs.setNull(6,java.sql.Types.INTEGER );
			}
			ResultSet rs = cs.executeQuery();
			return true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static boolean  inviteFriends(Invited invited){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call InviteFriend(?,?,?,?,?,?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setLong(1, invited.getGameID());
			cs.setInt(2, invited.getPlayerID());
			cs.setTimestamp(3, invited.getSentDate());
			if(invited.getSentDate()!=null) {
				cs.setTimestamp(4, invited.getRecieveDate());

			}else {
				cs.setNull(3, java.sql.Types.TIMESTAMP);
			}
			if(invited.getPoints()>=0) {
				cs.setInt(5, invited.getPoints());
			}else {
				cs.setNull(5,java.sql.Types.INTEGER );
			}
			if(invited.getTimesDied()>=0) {
				cs.setInt(6, invited.getTimesDied());
			}else {
				cs.setNull(6,java.sql.Types.INTEGER );
			}
			ResultSet rs = cs.executeQuery();
			return true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Integer>  getPlayersID(){
		ArrayList <Integer> toReturn = new ArrayList<Integer>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getPlayersId()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn.add(rs.getInt(i++));		
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static String  getPlayerPassword(int id){
		String toReturn = new String();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getPlayerPassword(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn = rs.getString(i++);	
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static Player  getPlayer(int id){
		Player toReturn=null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getPlayer(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn = new Player(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++), rs.getDate(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static Country  getCountry(int id){
		Country toReturn=null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getCountry(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn = new Country(rs.getInt(i++), rs.getString(i++));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static BestOF100Playes  getBest100ByGameID(long gameId){
		BestOF100Playes toReturn=null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getBest100ByGameID(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setLong(1, gameId);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn = new BestOF100Playes(rs.getLong(i++), rs.getTimestamp(i++), rs.getTimestamp(i++));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static CreateYourOwnMatchWithFriends  getGameWithFriendsByGameID(long gameId){
		CreateYourOwnMatchWithFriends toReturn=null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getCreatedGameWithFriendsByGameID(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setLong(1, gameId);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn = new CreateYourOwnMatchWithFriends(rs.getLong(i++), rs.getTimestamp(i++), rs.getTimestamp(i++),rs.getInt(i++),rs.getInt(i++),rs.getInt(i++));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static int getPlayerNumberOfGames(int id) {
		int count =0;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getNumOfGames(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
			count = rs.getInt(i++);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	public static int getCountBestOf100(int id) {
		int count =0;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call countBest100(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
			count = rs.getInt(i++);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}
	public static int getCountGamesWithFriends(int id) {
		int count =0;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call countFriendsGames(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
			count = rs.getInt(i++);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}
	public static ArrayList<String> getBest100GameIDPoints(int id) {
		ArrayList<String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getBest100GameIDPoints(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
			toReturn.add(new String(""+rs.getInt(i++)));
			toReturn.add(new String(""+rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}//System.out.println(toReturn);
		return toReturn;
	}
	public static ArrayList<String>  getFriendsMatchGameIDPoints(int id) {
		ArrayList<String> toReturn = new ArrayList<String>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getFriendsMatchGameIDPoints(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
			toReturn.add(new String(""+rs.getInt(i++)));
			toReturn.add(new String(""+rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}//System.out.println(toReturn);
		return toReturn;
	}
	
	
	
	public static ArrayList<PlayedIn>  getBest100GamesOfPlayer(int id){
		 ArrayList<PlayedIn> toReturn= new ArrayList<PlayedIn>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getPlayerBestOf100Games(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn.add(new PlayedIn(rs.getLong(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++),  rs.getInt(i++),  rs.getInt(i++),  rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static ArrayList<CreateYourOwnMatchWithFriends>  getCreatedOfPlayer(int id){
		 ArrayList<CreateYourOwnMatchWithFriends> toReturn= new ArrayList<CreateYourOwnMatchWithFriends>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getGamesCreated(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn.add(new CreateYourOwnMatchWithFriends(rs.getLong(i++), rs.getTimestamp(i++), rs.getTimestamp(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static ArrayList<Invited>  getInvitedOfPlayer(int id){
		 ArrayList<Invited> toReturn= new ArrayList<Invited>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getGamesInvited(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn.add(new Invited(rs.getLong(i++), rs.getInt(i++), rs.getTimestamp(i++),rs.getTimestamp(i++),rs.getInt(i++), rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	public static long  getMaxCreatedGameID(){
		long toReturn = 1;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getMaxGameID()}" ;
			CallableStatement cs = con.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				String record = "";
				int i=1;
				toReturn = rs.getLong(i++);		
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static ArrayList<Player> getFriendsOfPlayer(int playerID){
		ArrayList<Player> toReturn = new ArrayList<Player>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getFriendsOfPlayer(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, playerID);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				temp.add(rs.getInt(i++));		
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		for(Integer i : temp) {
			toReturn.add(DBConnection.getPlayer(i));
		}
		return toReturn;
	}
	
	
	public static ArrayList<GiftRankPlayer>  getGiftRankPlayer(int playerID){
		 ArrayList<GiftRankPlayer> toReturn= new ArrayList<GiftRankPlayer>();
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getPlayerRanksID(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, playerID);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn.add(new GiftRankPlayer(rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getDate(i++), rs.getBoolean(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static Gift  getGiftByGiftID(int giftID){
		Gift toReturn= null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getGiftByGiftID(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, giftID);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn=(new Gift(rs.getInt(i++),rs.getString(i++),rs.getString(i++),rs.getInt(i++)));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	public static Ranks  getRankByRankID(int rankID){
		Ranks toReturn=null;
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		try{
			String SQL = "{call getRankByRankID(?)}" ;
			CallableStatement cs = con.prepareCall(SQL);
			cs.setInt(1, rankID);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int i=1;
				toReturn=new Ranks(rs.getInt(i++), rs.getString(i++), rs.getInt(i++));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
	
	
	public static void main(String [] args) {
		//System.out.println(checkRankplayer(1, 4));
		///writeToFile("ssssssssss");
}
	
	
	
	
	
}
	
	

