package Data_provider;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataproviderTestng {
	static utilities.ExcelAllMethods xlsread=new utilities.ExcelAllMethods();
 public static String path=System.getProperty("user.dir")+"\\resources\\";
 	public static ArrayList<Object[]> read_login_data(){
 		
 		
 		String sheetname="Sheet3";
		String pathname=path+"TD_Create User.xlsx";
		
		xlsread.Xls_Reader(pathname);
		int maxrow=xlsread.getRowCount(sheetname);
		int maxcell=xlsread.getColumnCount(sheetname);
		ArrayList<Object[]> data_arrray=new ArrayList<Object[]>();
		
		for(int i=2;i<=maxrow;i++) {
			String Username=xlsread.getCellData(sheetname, "username", i);
			String password=xlsread.getCellData(sheetname,"password", i);
			
			data_arrray.add(new Object[] {Username,password});
		}
 		
 		return data_arrray;
 	}
	public static ArrayList<Object[]> read_contact_data() {
		String sheetname="Sheet2";
		String pathname=path+"TD_Create User.xlsx";
		
		//Xlsx_all_methods xlsread=new Xlsx_all_methods();
			xlsread.Xls_Reader(pathname);
			int maxrow=xlsread.getRowCount(sheetname);
			int maxcell=xlsread.getColumnCount(sheetname);
			ArrayList<Object[]> data_arrray=new ArrayList<Object[]>();
			
			for(int i=2;i<=maxrow;i++) {
				
				String Last_Name=xlsread.getCellData(sheetname, "Last Name", i);
				String first=xlsread.getCellData(sheetname,"First Name", i);
				String salut=xlsread.getCellData(sheetname,"Salutation", i);
				String phone=xlsread.getCellData(sheetname,"Phone", i);
			    String email=	xlsread.getCellData(sheetname,"Email", i);
				String title=xlsread.getCellData(sheetname,"Title", i);
				  

	   data_arrray.add(new Object[] {Last_Name,first,salut,phone,email,title});
				
				
			}
			
			return data_arrray;
			
	}
	
	
	
	@DataProvider(name="login_data")
	public static Iterator<Object[]> array_object_login(){
		
		ArrayList<Object[]> data_login=DataproviderTestng.read_login_data();
		
		
		return data_login.iterator() ;
		
		
	}	


	@DataProvider(name="contacts_data")
	public static Iterator<Object[]> array_object_contacts(){
		
       ArrayList<Object[]> dataread=DataproviderTestng.read_contact_data();
		
		
		return dataread.iterator() ;
			
			
	}
}
