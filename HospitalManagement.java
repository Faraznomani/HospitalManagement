import java.util.*;
import java.util.Date;
import java.io.*;
class Patient{		
			int patient_no;
			String name;
			char sex;
			int age;
			String address;
			long phone_no;
			int type_dept;
			int type_doctor;
			boolean referred;
			
			Patient()
			{
			}

			Patient(int patient_no,String name,char sex,int age,String address,long phone_no,int type_dept,int type_doctor,boolean referred)
			{
				this.patient_no=patient_no;
				this.name=name;
				this.sex=sex;
				this.age=age;
				this.address=address;
				this.phone_no=phone_no;
				this.type_dept=type_dept;
				this.type_doctor=type_doctor;
				this.referred=referred; 
			}
			
			void display_Patient_Details()
			{
				System.out.println("\n \n PATIENT NO"+patient_no);
				
				System.out.println("PATIENT NAME "+patient_no);
				System.out.println("PATIENT SEX"+name);
				System.out.println("PATIENT AGE "+sex);
				System.out.println("PATIENT ADDRESS"+age);
				System.out.println("PATIENT PHONE NO "+address);
				System.out.println("DOCTOR NO "+phone_no);
				System.out.println("DOCTOE DEPARTMENT "+type_doctor);
				System.out.println("REFERRED "+referred);
			}
	}

class IP  extends Patient{	int bed_no;
				String ward_type;
				Date admit_date;
				Date discharge_date;
				Bill bill;
				IP()
				{
					super();
				}
				IP(int patient_no,String name,char sex,int age,String address,long phone_no,int type_dept,int type_doctor,boolean referred,int bed_no,String ward_type,Date admit_date)
				{	
					super(patient_no,name,sex,age,address,phone_no,type_dept,type_doctor,referred);
					this.bed_no=bed_no;
					this.ward_type=ward_type;
					this.admit_date=admit_date;
					this.discharge_date=null;
					this.bill=null;
				}

				
			void display_Patient_Details()
			{
				super.display_Patient_Details();
				System.out.println("BED NO "+bed_no);
				System.out.println("WARD TYPE "+ward_type);
				System.out.println("ADMIT DATE "+admit_date);
				if (discharge_date!=null)
				{
					System.out.println("DISCHARGE DATE "+discharge_date);
					System.out.println("------BILL DETAILS----");
					bill.display_Bill_Details();
				}
					
			}
		}		

class Department{
			int dept_no;
			String name;

			Department(int dept_no,String name)
			{
				this.dept_no=dept_no;
				this.name=name;
			}
		}
class Appointment{
				int patient_no;
				int doctor_no;
				String app_date;
				String app_time;
			
			Appointment(int patient_no,int doctor_no,String app_date,String app_time)
				{
				this.patient_no=patient_no;
				this.doctor_no=doctor_no;
				this.app_date=app_date;
				this.app_time=app_time;
				}

			static void display_Appts(Doctor d)
			{
				System.out.println("\n \n--------DOCTOR DETAILS----");
				Doctor.display_Doctor_Details(d);
				System.out.println("-------APPOINTMENT LIST--------");
				 Iterator it=HospitalManagement.appointments.iterator();
               	                 while (it.hasNext())
              	                  {
               	                         Appointment a=(Appointment)it.next();
                                        if (a.doctor_no==d.doctor_no)
					{
						System.out.println("DATE :"+a.app_date);
						System.out.println("TIME :"+a.app_time);
						System.out.println("PATIENT NO :"+a.patient_no);


					}
				   }
			}
			
			static Appointment set_Appointment(int p_no,String adate,Doctor d)
			{	display_Appts(d);
				String t=null;
				 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

				System.out.println("\n \n----IS ANY TIME AVAILABLE FOR THIS DATE??ENTER Y/N---");
				try{
				String ch=br.readLine();
				if (ch.equalsIgnoreCase("y"))
				{
				System.out.println("------ENTER APPROPRIATE TIME----");
				t=br.readLine();
				
				return new Appointment(p_no,d.doctor_no,adate,t);
				}
				else
				{
					System.out.println("----SORRY,DOCTOR IS UNAVAILABLE----");
					return null;
				}
				}catch(IOException ex)
                                {System.out.println("Try again...input error occurred");
				return null;
                                }

				
			}
		}





class Doctor{//	const int all_days=1;
	//	const int even_days=2;
	//	const int odd_days=3
		int doctor_no;
		String name;
		String address;
		long phone_no;
		String starting_time;
		String ending_time;
	//	int available_days;
		int dept_no;
		int floor_no;

		Doctor()
		{
		}
		Doctor( int doctor_no,String name,String address,long phone_no/*,int available_days*/,String starting_time,String ending_time,int dept_no,int floor_no)
		{
			this.doctor_no=doctor_no;
                	this.name=name;
                	this.address=address;
                	this.phone_no=phone_no;
		//	this.available_days=available_days;//
                	this.starting_time=starting_time;
			this.ending_time=ending_time;
                	this.dept_no=dept_no;
	               	this.floor_no=floor_no;

		}
		
		Prescription examine_Patient(int p_no)
		{	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			Test t=new Test();
			int t_no=0;
			int pr_no=0;
			boolean admt=false;
			String dis,wardreq=null;
			Iterator it=HospitalManagement.prescriptions.iterator();
			while(it.hasNext())
			{
				Prescription pt=(Prescription)it.next();
				pr_no=pt.prescription_no;
			}
			pr_no=pr_no+1;
			System.out.println("PRESCRIPTION  NO: "+pr_no);
			try{
			System.out.println("\n ENTER DISEASE/PROBLEM PATIENT IS SUFFERING FROM");
			 dis=br.readLine();
			System.out.println("\n ANY TESTS PRESCRIBED??");
			String ch=br.readLine();
			if (ch.equalsIgnoreCase("y"))
			{	
				it=HospitalManagement.tests.iterator();
				while (it.hasNext())
				{
					 t=(Test)it.next();
					 System.out.println("\n TEST NO "+t.test_no+"\t NAME "+t.test_name);
				}
				System.out.println("\n ENTER TEST NUMBER");
				t_no=Integer.parseInt(br.readLine());
				it=HospitalManagement.tests.iterator();
				while (it.hasNext())
				{
					 t=(Test)it.next();
					 if (t.test_no==t_no)
					{
						break;
					}
				}
			}
			else
				t=null;
			System.out.println(" \n WILL THE PATIENT BE ADMITTED??Y/N");
			String c=br.readLine();
			if (c.equalsIgnoreCase("y"))
			{
				admt=true;
				System.out.println("--IS THERE ANY SPECIAL WARD CRITERIA FOR THIS PATIENT??Y/N---");
				String w=br.readLine();	
				if (w.equalsIgnoreCase("y"))
				{
					wardreq=br.readLine();
				}
			}
			else
				admt=false;
			}catch(IOException io)
			{
				System.out.println("INPUT ERROR OCCURRED....TRY AGAIN");
				return null;
			}
			catch(NumberFormatException n)
			{
				System.out.println("ENTER VALID INPUT!!!!!!");
				return null;
			}
			return new Prescription(pr_no,this.doctor_no,p_no,dis,t,admt,wardreq);
		}
		
		static void display_Doctor_Details(Doctor d)
		{
			System.out.println("DOCTOR NO: "+d.doctor_no);
			System.out.println("NAME: "+d.name);
                        System.out.println("DEPARTMENT NO: "+d.dept_no);
                        System.out.println("STARTING TIME: "+d.starting_time);
                        System.out.println("ENDING TIME: "+d.ending_time);
             

		}
	}

class Prescription{	int prescription_no;
			int doctor_no;
			int patient_no;
			String disease;
			Test test;
			boolean admit;
			String ward_req;

			Prescription()
			{
			}
			Prescription(int prescription_no,int doctor_no,int patient_no,String disease,Test test,boolean admit,String ward_req)
			{	this.prescription_no=prescription_no;
				this.doctor_no=doctor_no;
				this.patient_no=patient_no;
				this.disease=disease;
				this.test=test;
				this.admit=admit;
				this.ward_req=ward_req;
			}

			void display_prescription()
			{	System.out.println("\n \n---PRESCRIPTION----");
				System.out.println("PRES NO: "+prescription_no);
				System.out.println("PATIENT NO: "+patient_no);
				System.out.println("DOCTOR NO: "+doctor_no);
				System.out.println("DISEASE: "+disease);
				if (test!=null)
					test.show_Test_Details();
				if (admit==true)
				{
					System.out.println("---PATIENT SHOULD BE ADMITTED----");
					if (ward_req!=null)
						System.out.print(" IN "+ward_req);
				}			
			}
		}


class Test{	int test_no;
		String test_name;
		double charge;
		
		Test()
		{
		}

		Test(int test_no,String test_name,double charge)
		{
			this.test_no=test_no;
			this.test_name=test_name;
			this.charge=charge;
		}

		void show_Test_Details()
		{
			System.out.println("TEST NO "+test_no);
			System.out.println("TEST NAME "+test_name);
			System.out.println("CHARGE "+charge);
		}
		
		static String conduct_Test(Prescription pr)
		{
			System.out.println("\n \n-------PERFORMING "+pr.test.test_name+"------");
			System.out.println("---ENTER RESULT---");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			try{
				return br.readLine();
			}catch(IOException ex)
			{
				System.out.println("---IO ERROR OCCURRED..TRY AGAIN---");
			}
			return null;
		}
			
	}


class Bed{
		int bed_no;
		int doctor_no;
		int patient_no;
		boolean occupied;
		Bed()
		{
		}

		Bed(int bed_no)
		{	this.bed_no=bed_no;
			this.occupied=false;
			patient_no=0;
			doctor_no=0;
		}

		void display_Bed_Details()
		{
			System.out.println("\n BED NO: "+bed_no);
			if (occupied==true)
			{
				System.out.println("PATIENT NO: "+patient_no);
				System.out.println("DOCTOR NO: "+doctor_no);
			}
		}

	
	}


class Ward{	Bed beds[];
		int no_of_beds;
		double charges_per_day;
		Ward()
		{
		}
		Ward(int no_of_beds,double charges_per_day,int start_bed_no)
		{
			this.no_of_beds=no_of_beds;
			this.charges_per_day=charges_per_day;
			beds=new Bed[no_of_beds];
			for (int i=0;i<no_of_beds;i++)
			{	
				beds[i]=new Bed(start_bed_no++);
				
			}
			
		}
		
		void display_Ward_Details()
		{
			System.out.println("Ward capacity"+no_of_beds);
			System.out.println("charge per day "+charges_per_day);
			for (int i=0;i<no_of_beds;i++)
			{
				beds[i].display_Bed_Details();
			}
		}

		
	}


class GeneralWard extends Ward{ 
				char type;
				GeneralWard()
				{
					super();
				}
				GeneralWard(int no_of_beds,double charges_per_day,char type,int start_bed_no)
				{
					super(no_of_beds,charges_per_day,start_bed_no);
					this.type=type;
				}

				void display_Ward_Details()
				{
					System.out.println("TYPE: "+type);
					super.display_Ward_Details();
				}
			    }

class ICU extends Ward{
				ICU()
				{
					super();
				}
			 	ICU(int no_of_beds,double charges_per_day,int start_bed_no)
				{
					super(no_of_beds,charges_per_day,start_bed_no);
				}
		}

class PrivateWard extends Ward{		PrivateWard()
					{
						super();
					}
					
					PrivateWard(double charges_per_day,int start_bed_no)
					{
						super(1,charges_per_day,start_bed_no);//privateward will have only 1 bed
					}
			}


class Bill{
			int bill_no;
			Date bill_date;
			double total_amount;
			
			Bill()
			{
			}
			Bill(int bill_no,Date bill_date,double total_amount)
			{
				this.bill_no=bill_no;
				this.bill_date=bill_date;
				this.total_amount=total_amount;
			} 

			void display_Bill_Details()
			{
				System.out.println("BILL NO: "+bill_no);
				System.out.println("BILL DATE: "+bill_date);
				System.out.println("TOTAL AMOUNT: "+total_amount);
				
			}
		}

class Administration{
		static Patient  set_PatientInfo()
		{	// if referred==false thenreturn type of department ptient should refer to,set type_dept and type_doctor accordingly
			//if referred==true then set type_dept
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			      Patient p=new Patient();

			try{
			int pno=0;
			Iterator it=HospitalManagement.patients.iterator();
			while(it.hasNext())
			{
				Patient pt=(Patient)it.next();
				pno=pt.patient_no;
			}
			p.patient_no=pno+1;
			System.out.println("PATIENT NO: "+p.patient_no);
			System.out.println("---ENTER NAME--");
			p.name=br.readLine();
			 System.out.println("---ENTER ADDRESS--");
			p.address=br.readLine();
			 System.out.println("---ENTER PHONE NO--");
			p.phone_no=Long.parseLong(br.readLine());
			System.out.println("---ENTER AGE--");
			p.age=Integer.parseInt(br.readLine());
			System.out.println("---ENTER SEX--M/F");
			p.sex=(char)br.read();
			System.out.println("---ENTER DEPARTMENT NO ---");
			it=HospitalManagement.departments.iterator();
			while (it.hasNext())
			{
				Department d=(Department)it.next();
				System.out.println(d.dept_no+d.name);
			}	
			
			
			boolean flag=true;
			while(flag==true)
			{
				try{
					p.type_dept=Integer.parseInt(br.readLine());
					flag =false;
			 	   }
                        	catch(NumberFormatException n)
                        	{	flag=true;
                                	System.out.println("--PLEASE ENTER A VALID NO---");
                        	}
			}
			 System.out.println("---DO YOU HAVE A REFERENCE ?ENTER Y OR N");
			if (br.readLine().equalsIgnoreCase("y"))
			{
				p.referred=true;
			}
			   else
                        {
                                p.referred=false;
                        }
			System.out.println("--ENTER DOCTOR NO--");
			it=HospitalManagement.doctors.iterator();
			while (it.hasNext())
			{
				Doctor d=(Doctor)it.next();
				if (d.dept_no==p.type_dept)
				{
					System.out.println(d.doctor_no+d.name);
				}	
			}
			p.type_doctor=Integer.parseInt(br.readLine());
			}
			catch (IOException io)
			{
				System.out.println("--AN ERROR OCCURRED WHILE ENTERING DATA..PLEASE TRY AGAIN...");
				return null;
			}
			catch(NumberFormatException n)
			{
				System.out.println("ENTER VALID INPUT!!!!!!");
				return null;
			}
			return p;
		}

		static IP admit_Patient(Patient p,Prescription pr)
		{ 	GeneralWard g=new GeneralWard();
			PrivateWard prvt=new PrivateWard();
			ICU ic=new ICU();
// add
			Date date=new Date();
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		   try{
		        int n;
			if ((pr.ward_req==null)||(pr.ward_req.equalsIgnoreCase("Private")))
			{	
			    if (pr.ward_req==null)
			    {    System.out.println("---SELECT YOUR PREFERENCE---");
				 System.out.println("1-->GENERAL \n 2-->PRIVATE");
				 n=Integer.parseInt(br.readLine());
			    }
			    else
				n=2;
				switch(n)
				{
				 case 1:
					Iterator it=HospitalManagement.generalward.iterator();
					while (it.hasNext())
					{
						g=(GeneralWard)it.next();
						if (g.type==p.sex)
							break;
					}
					for (int i=0;i<g.beds.length;i++)
					{
						if (g.beds[i].occupied==false)
						{	
							
							g.beds[i].patient_no=p.patient_no;
							g.beds[i].doctor_no=p.type_doctor;
							g.beds[i].occupied=true;
							HospitalManagement.beds.add(g.beds[i]);
							System.out.println("---YOU HAVE BEEN ALLOTED BED NO: "+g.beds[i].bed_no);
							return new IP(p.patient_no,p.name,p.sex,p.age,p.address,p.phone_no,p.type_dept,p.type_doctor,p.referred,g.beds[i].bed_no,"General",date);
						}
					}
					System.out.println("---SORRY, NO BED IN GENERAL WARD IS AVAILABLE---");
					return null;
					
				case 2: 
					it=HospitalManagement.privatewards.iterator();
					while (it.hasNext())
					{
						prvt=(PrivateWard)it.next();
						if (prvt.beds[0].occupied==false)
						{
						
							prvt.beds[0].patient_no=p.patient_no;
							prvt.beds[0].doctor_no=p.type_doctor;
							prvt.beds[0].occupied=true;
							HospitalManagement.beds.add(prvt.beds[0]);
							System.out.println("---YOU HAVE BEEN ALLOTED BED NO: "+prvt.beds[0].bed_no);
							return new IP(p.patient_no,p.name,p.sex,p.age,p.address,p.phone_no,p.type_dept,p.type_doctor,p.referred,prvt.beds[0].bed_no,"Private",date);
						}

					}
					System.out.println("---SORRY,NO BED IN PRIVATE WARD IS AVAILABLE---");
					return null;
					
				default:System.out.println("---INVALID CHOICE--");
					return null;	
				}
			   }
			   else
			   {
				Iterator it=HospitalManagement.icu.iterator();
					
					
						ic=(ICU)it.next();
						
					
					for (int i=0;i<ic.beds.length;i++)
					{
						if (ic.beds[i].occupied==false)
						{	
							
							ic.beds[i].patient_no=p.patient_no;
							ic.beds[i].doctor_no=p.type_doctor;
							ic.beds[i].occupied=true;
							HospitalManagement.beds.add(ic.beds[i]);
							System.out.println("---YOU HAVE BEEN ALLOTED BED NO: "+ic.beds[i].bed_no);
							return new IP(p.patient_no,p.name,p.sex,p.age,p.address,p.phone_no,p.type_dept,p.type_doctor,p.referred,ic.beds[i].bed_no,"ICU",date);
						}
					}
					System.out.println("---SORRY,NO BED IN ICU WARD IS AVAILABLE---");
					return null;
			   }
			}
			catch(IOException ex)
			{
				System.out.println("---I/O ERROR OCCURRED...TRY AGAIN!");
				return null;
			}	
			catch(NullPointerException n)
			{

				System.out.println("---INVALID INPUT...TRY AGAIN!");
				return null;
			}
			
		}
		
		static boolean discharge_Patient(IP p)
		{
			Date date=new Date();
			p.discharge_date=date;
			Date d=p.admit_date;
			double rate=0;
			int b_no=0;
			long diff=(p.discharge_date.getTime()-p.admit_date.getTime())/(24*60*60*1000);
			if (p.ward_type.equalsIgnoreCase("Private"))
				rate=1000;
			if (p.ward_type.equalsIgnoreCase("General"))
				rate=800;
			if (p.ward_type.equalsIgnoreCase("ICU"))
				rate=2000;
			double amt=diff*rate;
			Iterator itr=HospitalManagement.bills.iterator();
			while (itr.hasNext())
			{
				Bill bill=(Bill)itr.next();
				b_no=bill.bill_no;
			}
			b_no=b_no+1;
			Bill bil=new Bill(b_no,p.discharge_date,amt);
			if (bil!=null)
			{	p.bill=bil;
				HospitalManagement.bills.add(bil);
				System.out.println("\n \n----YOUR BILL----");
				bil.display_Bill_Details();
				HospitalManagement.admitted_patients.add(p);
				Iterator it=HospitalManagement.beds.iterator();
				
				while (it.hasNext())
				{
					Bed b=(Bed)it.next();
					if (b.bed_no==b_no)
					{
						HospitalManagement.beds.remove(b);
						b.patient_no=0;
						b.doctor_no=0;
						b.occupied=false;
						break;
					}
				}
				return true; 	
			}
			return false;
		}

		static IP shift_Patient(IP p)
		{ 	GeneralWard g=new GeneralWard();
			PrivateWard prvt=new PrivateWard();
			ICU ic=new ICU();
			Date date=p.admit_date;
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		   try{	Iterator itr=HospitalManagement.beds.iterator();
			while (itr.hasNext())
			{
				Bed b=(Bed)itr.next();
				if (b.patient_no==p.patient_no)
				{HospitalManagement.beds.remove(b);
				 break;
				}
			}
		        int n;
			System.out.println("---SELECT YOUR PREFERENCE---");
			System.out.println("1-->GENERAL \n 2-->PRIVATE");
			n=Integer.parseInt(br.readLine());
			switch(n)
			{
				case 1:
					Iterator it=HospitalManagement.generalward.iterator();
					while (it.hasNext())
					{
						g=(GeneralWard)it.next();
						if (g.type==p.sex)
							break;
					}
					for (int i=0;i<g.beds.length;i++)
					{
						if (g.beds[i].occupied==false)
						{	
							
							g.beds[i].patient_no=p.patient_no;
							g.beds[i].doctor_no=p.type_doctor;
							g.beds[i].occupied=true;
							HospitalManagement.beds.add(g.beds[i]);
							System.out.println("---YOU HAVE BEEN ALLOTED BED NO: "+g.beds[i].bed_no);
							return new IP(p.patient_no,p.name,p.sex,p.age,p.address,p.phone_no,p.type_dept,p.type_doctor,p.referred,g.beds[i].bed_no,"General",date);
						}
					}
					System.out.println("---SORRY,NO BED IN GENERAL WARD IS AVAILABLE---");
					return null;
					
				case 2: 
					it=HospitalManagement.privatewards.iterator();
					while (it.hasNext())
					{
						prvt=(PrivateWard)it.next();
						if (prvt.beds[0].occupied==false)
						{
						
							prvt.beds[0].patient_no=p.patient_no;
							prvt.beds[0].doctor_no=p.type_doctor;
							prvt.beds[0].occupied=true;
							HospitalManagement.beds.add(prvt.beds[0]);
							System.out.println("---YOU HAVE BEEN ALLOTED BED NO: "+prvt.beds[0].bed_no);
							return new IP(p.patient_no,p.name,p.sex,p.age,p.address,p.phone_no,p.type_dept,p.type_doctor,p.referred,prvt.beds[0].bed_no,"Private",date);
						}

					}
					System.out.println("---SORRY,NO BED IN PRIVATE WARD IS AVAILABLE---");
					return null;
					
				default:System.out.println("---INVALID CHOICE--");
					return null;	
				
			  }
			 
		      }
			catch(IOException ex)
			{
				System.out.println("---I/O ERROR OCCURRED...TRY AGAIN!");
				return null;
			}	
			catch(NullPointerException n)
			{

				System.out.println("---INVALID INPUT...TRY AGAIN!");
				return null;
			}
			
		}
	}



class HospitalManagement{
				static ArrayList 

s=new ArrayList();
				static ArrayList doctors=new ArrayList();
				static ArrayList departments=new ArrayList();
				static ArrayList appointments=new ArrayList();
				static ArrayList tests=new ArrayList();
				static ArrayList prescriptions=new ArrayList();
				static ArrayList generalward=new ArrayList();
				static ArrayList privatewards=new ArrayList();
				static ArrayList icu=new ArrayList();
 				static ArrayList admitted_patients=new ArrayList();
				static ArrayList beds=new ArrayList();
				static ArrayList bills=new ArrayList();
				public static void main(String args[])
				{	Department dp1=new Department(1,"GASTROENTROLOGY");
					Department dp2=new Department(2,"NEUROLOGY");
					Department dp3=new Department(3,"ORTHOPAEDICS");
					Department dp4=new Department(4,"CARDIOLOGY");
					departments.add(dp1);
					departments.add(dp2);
					departments.add(dp3);
					departments.add(dp4);	
					Doctor d1=new Doctor(101,"Pranjal Jain","dffdf",434343,"11:30 am","2:30 pm",1,1);
              				Doctor d2=new Doctor(102,"Kartik Sharma","dsfsff",2442434,"1:30 pm","4:30 pm",2,1);
					Doctor d3=new Doctor(103,"Parul Ranade","dfsfsfd",243444,"3:00 pm","6:00 pm",3,2);
					Doctor d4=new Doctor(104,"Neha Gattani","fddffdf",343444,"10:00 am","1:00 pm",4,2);
					doctors.add(d1);
					doctors.add(d2);
					doctors.add(d3);
					doctors.add(d4);
					Test t1=new Test(1,"X RAY",100.00);
					Test t2=new Test(2,"Blood Test",25.00);
					Test t3=new Test(3,"CT SCAN",500.00);
					tests.add(t1);
					tests.add(t2);
					tests.add(t3);
					GeneralWard gw1=new GeneralWard(10,800,'m',1);
					GeneralWard gw2=new GeneralWard(10,800,'f',11);
					generalward.add(gw1);
					generalward.add(gw2);
					PrivateWard pw1=new PrivateWard(1000.0,21);
					PrivateWard pw2=new PrivateWard(1000.0,22);
					PrivateWard pw3=new PrivateWard(1000.0,23);
					PrivateWard pw4=new PrivateWard(1000.0,24);
					PrivateWard pw5=new PrivateWard(1000.0,25);
					privatewards.add(pw1);
					privatewards.add(pw2);
					privatewards.add(pw3);
					privatewards.add(pw4);
					privatewards.add(pw5);
					ICU i1=new ICU(5,2000,26);
					icu.add(i1); 
					BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
					char c='y';
				   do{
					
					System.out.println("\n \n------WELCOME TO CHL-APOLLO------");
					System.out.println("\n -----1--->REGISTER A PATIENT--- \n -----2-->TAKE APPOINTMENT--- \n -----3-->ADMIT PATIENT--- \n -----4-->DISCHARGE PATIENT--- \n -----5-->SHOW LIST OF ADMITTED PATIENTS--- \n -----6--->SHIFT A PATIENT--- \n -----7--->SHOW APPOINTMENTS FOR A DOCTOR--- \n -----8--->SHOW PATIENT DETAILS--- \n -----9--->SHOW DOCTOR DETAILS--- \n -----10--->SHOW TESTS--- \n -----11--->MODIFY TEST DETAILS--- \n -----12--->ADD NEW DOCTOR--- \n -----13--->ADD NEW DEPARTMENT--- \n -----14--->ADD NEW TEST--- \n -----15--->EXIT---");
					System.out.println("-----ENTER CHOICE--");
					int n=0;
					try{
						n=Integer.parseInt(br.readLine());
					}catch(IOException ex)
					{
						System.out.println("----error occureed while input please try again----");
					}
					catch(NumberFormatException nu)
					{
						System.out.println("ENTER VALID INPUT!!!!!!TRY AGAIN");
					}
						
					switch(n)
					{
					 case 1:
						Patient p1=Administration.set_PatientInfo();
						if (p1==null)
							System.out.println("--DATA CANNOT BE ENTERED..TRY AGAIN--");
						else
						{
							System.out.println("--SUCCESSFULLY REGISTERED...PLEASE TAKE YOUR APPOINTMENT--");
							patients.add(p1);
				       		 }
						System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
						try{
							c=(char)br.read();
						}catch(IOException ex)
						{
							System.out.println("----error occureed while input plaease try again----");
							System.exit(0);
						}
						break;
				      	case 2:try{
							boolean found=false;
							System.out.println("\n \n---ENTER PATIENT NO---");
							int pno=Integer.parseInt(br.readLine());
							int dno=0;
							Iterator it=patients.iterator();
							while (it.hasNext())
							{
								Patient p=(Patient)it.next();
								if (p.patient_no==pno)
								{
									System.out.println("---PATIENT FOUND---");
									found=true;	
									Doctor doc=new Doctor();
									dno=p.type_doctor;
									Iterator i=doctors.iterator();
									while (i.hasNext())
									{
										doc=(Doctor)i.next();
										if (doc.doctor_no==dno)
										{
											break;
										}
									}
									System.out.println("\n \n--ENTER APPOINTMENT DATE----");
									String a_date=br.readLine();
									Appointment a=Appointment.set_Appointment(pno,a_date,doc);
									if (a!=null)
									{
										appointments.add(a);
										System.out.println("---------APPOINTMENT FIXED--------");
										//NOW DOCTOR EXAMINES THE PATIENT 
										Prescription pr=doc.examine_Patient(pno);
										HospitalManagement.prescriptions.add(pr);
										pr.display_prescription();
										if (pr.test!=null)
										{
											String report=Test.conduct_Test(pr);
											System.out.println("--YOUR TEST REPORT IS --"+report);
										}
									}
									else
									{
										System.out.println("---PLEASE TAKE ANOTHER APPPOINTMENT----");
									}
									break;
								}
							}
							if (found==false)
							{
								System.out.println("----INVALID PATIENT NO----");
							}
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						      }catch(IOException ex)
							{
								System.out.println("---SORRY , IO ERROR OCCURRED---");
							}	
							catch(NumberFormatException nu)
							{
								System.out.println("ENTER VALID INPUT!!!!!!TRY AGAIN");
							}
						
							break;
					case 3:try{	IP ip1=new IP();
							Prescription pr=new Prescription();
							boolean found=false;
							System.out.println("\n \n--ENTER PATIENT NO--");
							int pno=Integer.parseInt(br.readLine());
							Iterator i=prescriptions.iterator();
							while (i.hasNext())
							{
								 pr=(Prescription)i.next();
								if ((pr.patient_no==pno) && (pr.admit==true))
								{
									System.out.println("---PRESCRIPTION FOUND---");
									break;
								}
							}
							Iterator it=patients.iterator();
							while (it.hasNext())
							{
								Patient p=(Patient)it.next();
								if (p.patient_no==pr.patient_no)
								{
									System.out.println("---PATIENT FOUND---");
									found=true;
									do{
										ip1=Administration.admit_Patient(p,pr);
									}while(ip1==null);
									admitted_patients.add(ip1);
									break;
								}
							}	
							if (found==false)
							{
								System.out.println("----INVALID PATIENT NO----");
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
							}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}
							catch(NumberFormatException num)
							{
								System.out.println("ENTER VALID INPUT!!!!!!TRY AGAIN");
							}
							break;
					case 4:try{	
							boolean found=false;
							System.out.println("--ENTER PATIENT NO--");
							int pno=Integer.parseInt(br.readLine());
							Iterator it=admitted_patients.iterator();
							while (it.hasNext())
							{
								IP p=(IP)it.next();
								if (p.patient_no==pno)
								{
									System.out.println("---PATIENT FOUND---");
									admitted_patients.remove(p);
									found=true;
									if (Administration.discharge_Patient(p)==true)
									{
									System.out.println("----YOU ARE DISCHARGED FROM THE HOSPITAL---");
									break;
									}
									else
									{
										System.out.println("----YOU COULD NOT BE DISCHARGED FROM THE HOSPITAL---");
										admitted_patients.add(p);
									}
									
								}
							}	
							if (found==false)
							{
								System.out.println("----INVALID PATIENT NO----");
							}
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
						{
							System.out.println("----error occureed while input plaease try again----");
							System.exit(0);
						}
						catch(NumberFormatException numb)
						{
							System.out.println("ENTER VALID INPUT!!!!!!TRY AGAIN");
						}
						break;
					case 5://try{	Iterator it=beds.iterator();
						//	while (it.hasNext())
						//	{
						//		Bed b=(Bed)it.next();
						//		b.display_Bed_Details();
						//	}
						//	System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
						//	c=(char)br.read();
						//}catch(IOException ex)
						//{
						//	System.out.println("----error occureed while input plaease try again----");
						//	System.exit(0);
						//}
						//break;
						try{	boolean found=false;
							System.out.println("\n \n---ENTER PATIENT NO---");
							int pno=Integer.parseInt(br.readLine());
							Iterator it=admitted_patients.iterator();
							while (it.hasNext())
							{
								IP p=(IP)it.next();
								if (p.patient_no==pno)
								{	found=true;
									p.display_Patient_Details();
									break;
								}
							}
							if (found==false)
							{
								System.out.println("---INVALID PATIENT NO.--");		
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;
					case 6:try{
							boolean found=false;
							IP ip1=new IP();
							System.out.println("--ENTER PATIENT NO--");
							int pno=Integer.parseInt(br.readLine());
							Iterator it=admitted_patients.iterator();
							while (it.hasNext())
							{
								IP p=(IP)it.next();
								if (p.patient_no==pno)
								{
									System.out.println("---PATIENT FOUND---");
									found=true;
									System.out.println("---YOUR CURRENT BED NO IS---"+p.bed_no);
									admitted_patients.remove(ip1);
										do{
										ip1=Administration.shift_Patient(p);
									}while(ip1==null);
									admitted_patients.add(ip1);
									break;
								}
							}	
							if (found==false)
							{
								System.out.println("----INVALID PATIENT NO----");
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						   }catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}
							catch(NumberFormatException num)
							{
								System.out.println("ENTER VALID INPUT!!!!!!TRY AGAIN");
							}
							break;
					case 7:try{
							System.out.println("--ENTER DOCTOR NO---");
							Iterator it=doctors.iterator();
							while(it.hasNext())
							{
								Doctor d =(Doctor)it.next();
								Appointment.display_Appts(d);
								break;
							}
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}
						break;
					case 8:try{	boolean found=false;
							System.out.println("\n \n---ENTER PATIENT NO---");
							int pno=Integer.parseInt(br.readLine());
							Iterator it=patients.iterator();
							while (it.hasNext())
							{
								Patient p=(Patient)it.next();
								if (p.patient_no==pno)
								{	found=true;
									p.display_Patient_Details();
									break;
								}
							}
							if (found==false)
							{
								System.out.println("---INVALID PATIENT NO.--");		
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;		
					case 9:try{	boolean found=false;
							System.out.println("\n \n---ENTER DOCTOR NO---");
							int dno=Integer.parseInt(br.readLine());
							Iterator it=doctors.iterator();
							while (it.hasNext())
							{
								Doctor d=(Doctor)it.next();
								if (d.doctor_no==dno)
								{	found=true;
									Doctor.display_Doctor_Details(d);
									break;
								}
							}
							if (found==false)
							{
								System.out.println("---INVALID DOCTOR NO.--");		
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;	
					case 10:try{	boolean found=false;
							System.out.println("\n \n---ENTER TEST NO---");
							int tno=Integer.parseInt(br.readLine());
							Iterator it=tests.iterator();
							while (it.hasNext())
							{
								Test t=(Test)it.next();
								if (t.test_no==tno)
								{	found=true;
									t.show_Test_Details();
									break;
								}
							}
							if (found==false)
							{
								System.out.println("---INVALID TEST NO.--");		
							}
							
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;		
					case 11:try{
						System.out.println("---ENTER TEST NO---");
						int tno=Integer.parseInt(br.readLine());
						System.out.println("----ENTER NEW CHARGE--");
						double rate=Double.parseDouble(br.readLine());
						Iterator it=tests.iterator();
						{
							while(it.hasNext())
							{
								Test t=(Test)it.next();
								if (tno==t.test_no)
								{
									tests.remove(t);
									t.charge=rate;
									tests.add(new Test(tno,t.test_name,rate));
									break;
								}
							}
						}
						System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;
					case 12:try{	
							int d_no=0;
							Iterator it=doctors.iterator();
							while(it.hasNext())
							{
								Doctor d=(Doctor)it.next();
								d_no=d.doctor_no;
							}
							d_no=d_no+1;
							System.out.println("---DOCTOR NO: "+d_no);
							System.out.println("---ENTER DOCTOR NAME---");
							String name=br.readLine();
							
							System.out.println("---ENTER ADDRESS---");
							String adr=br.readLine();

							System.out.println("---ENTER PHONE NO---");
							Long ph=Long.parseLong(br.readLine());

							System.out.println("---ENTER DOCTOR DEPARTMENT NO---");
							it=departments.iterator();
							while (it.hasNext())
							{
								Department d=(Department)it.next();
								System.out.println(d.dept_no+d.name);
							}
							int dpno=Integer.parseInt(br.readLine());

							System.out.println("---ENTER DOCTOR STARTING TIME---");
							String stime=br.readLine();

							System.out.println("---ENTER DOCTOR ENDING TIME---");
							String etime=br.readLine();

							System.out.println("---ENTER FLOOR NO---");
							int fno=Integer.parseInt(br.readLine());
							Doctor doc=new Doctor(d_no,name,adr,ph,stime,etime,dpno,fno);
							doctors.add(doc);
							System.out.println("---DOCTOR ADDED---");
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;	
					case 13:try{
						System.out.println("---ENTER DEPARTMENT NAME---");
						String dname=br.readLine();
						int dno=0;
						Iterator it=departments.iterator();
						{
							while(it.hasNext())
							{
								Department d=(Department)it.next();
								dno=d.dept_no;
							}
						}
						departments.add(new Department(dno+1,dname));
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input please try again----");
								System.exit(0);
							}	
						break;	
					case 14:try{
						System.out.println("---ENTER TEST NAME---");
						String tname=br.readLine();
						int tno=0;
						Iterator it=tests.iterator();
						{
							while(it.hasNext())
							{
								Test t=(Test)it.next();
								tno=t.test_no;
							}
						}
						System.out.println("---ENTER RATE---");
						double rate=Double.parseDouble(br.readLine());
						tests.add(new Test(tno+1,tname,rate));
							System.out.println("---DO YOU WISH TO CONTINUE?Y/N--");
							c=(char)br.read();
						}catch(IOException ex)
							{	System.out.println("----error occureed while input plaease try again----");
								System.exit(0);
							}	
						break;		
					case 15:System.exit(0);
						break;
					default:System.out.println("---INVALID CHOICE--");
					}
				}
				while(c=='y'||c=='Y');
			}
		}

