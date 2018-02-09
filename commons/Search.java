package couponInventorySystem.commons;

import java.util.ArrayList;

public class Search {

	Coupon_FinalPrice al [];
	
	public Search(int size) {
		al = new Coupon_FinalPrice[size];
	}
	
	public Coupon_FinalPrice[] getAl() {
		return al;
	}

	public void setAl(Coupon_FinalPrice[] al) {
		this.al = al;
	}

	public String output(int i) {
		
		return al[i].getA().getCoupon_site() + " , " + al[i].getA().product_name + " , " + al[i].getA().product_price + " , " + al[i].getA().getDiscount_rate() + " , " +
				al[i].getA().getExp_period() + " , " + al[i].getA().getStatus_of_coupon() + " , " + al[i].getFinal_price();
		
	}
	
	public String[] output1(int i) {
		
		String arr [] = {al[i].getA().getCoupon_site(),al[i].getA().product_name,String.valueOf(al[i].getA().product_price), String.valueOf(al[i].getA().getDiscount_rate()),
				String.valueOf(al[i].getA().getExp_period()),al[i].getA().getStatus_of_coupon(),String.valueOf(al[i].getFinal_price())};
		return  arr;
		
	}
	
	/*public void print() {
		int i=0;
		System.out.println(al);
		System.out.println(al[0]);
		while(i<al.length) {
			System.out.println(al[i].getA().getProduct_name());
			i++;
		}
	}*/

	///////////////////////////////////////////////////////////////////////
	//Function to copy coupons from linked list to array
	///////////////////////////////////////////////////////////////////////
	public void copyLinkedToArray(ArrayLinkedList LLlist) {
		//LLNode<Coupon> location = LLlist.getHead();
		ArrayLLNode[] location = LLlist.getHead();
		int index = 0;
		int arr_index = 0;
		//while(location!=null) {
		while(arr_index!= -1 && location[arr_index]!=null) {
			double final_price = (1-location[arr_index].getData().getDiscount_rate()/100)*location[arr_index].getData().getProduct_price();
			//System.out.println("index"+index+arr_index);
			//System.out.println(location[arr_index].getData());
			
			al[index] = new Coupon_FinalPrice(location[arr_index].getData(),final_price);
			//System.out.println(al[index]);
			//location = location.getLink();
			arr_index = location[arr_index].getLink();
			index++;
		}//remove this return statement, just used for debugging
		//return LLlist.getHead()!=null;
		/*System.out.println("UnSorted order, after copying linked to array");
		for(Coupon_FinalPrice x:al) {
			System.out.println(x.getA().getCoupon_site()+" , "+x.getA().getProduct_name()+" , "+x.getA().getProduct_price()+" , "+x.getA().getDiscount_rate()+" , "+
					x.getA().getExp_period()+" , "+x.getA().getStatus_of_coupon()+" , "+x.getFinal_price());
		}*/
	}
	//"Coupon Provider","Product Name","Price","Discount Rate","Expiration","Redeemed/Unused
		public void sort(String sort_key) {
			int bool = 0;
			Coupon_FinalPrice C;
			for(int i=0; i<al.length-1;i++) {
				for(int j=0;j<al.length-1-i;j++) {
					switch (sort_key) {
						case "Coupon Provider":{
							bool = al[j+1].a.getCoupon_site().compareToIgnoreCase(al[j].a.getCoupon_site());
							break;
						}
						case "Product Name":{
							//System.out.println("count of j : "+j);
							String y = al[j].a.getProduct_name();
							String x= al[j+1].a.getProduct_name();
							//System.out.println("x : "+x+" , y : "+y);
							bool = x.compareToIgnoreCase(y);
							break;
						}
						case "Price":{
							if(al[j+1].a.getProduct_price() < al[j].a.getProduct_price())
								bool = -1;
							else
								bool = 1;
							break;
						}	
						case "Discount Rate":{
							if(al[j+1].a.getDiscount_rate() < al[j].a.getDiscount_rate())
								bool = -1;
							else
								bool = 1;
							break;
						}	
						case "Expiration":{
							int y = al[j].a.getExp_period();
							int x= al[j+1].a.getExp_period();
							//System.out.println("x : "+x+" , y : "+y);
							if(al[j+1].a.getExp_period() < al[j].a.getExp_period())
								bool = -1;
							else
								bool = 1;
							break;
						}
						case "Redeemed/Unused":{
							bool = al[j+1].a.getStatus_of_coupon().compareToIgnoreCase(al[j].a.getStatus_of_coupon());
							break;
						}
						case "finalprice":{
							double var1 = al[j+1].final_price;
							double var2 = al[j].final_price;
							if(var1<var2)
								bool = -1;
							else
								bool = 1;
							break;
						}
					}
					if (bool < 0) {
						C = al[j];
						al[j] = al[j+1];
						al[j+1] = C;
					}
						
				}
			}
			/*System.out.println("Sorted order");
			for(Coupon_FinalPrice x:al) {
				System.out.println(x.getA().getCoupon_site()+" , "+x.getA().getProduct_name()+" , "+x.getA().getProduct_price()+" , "+x.getA().getDiscount_rate()+" , "+
						x.getA().getExp_period()+" , "+x.getA().getStatus_of_coupon()+" , "+x.getFinal_price());
			}*/
		}
		
		///////////////////////////////////////////////////////////////////////
		//Liner search algorithm
		///////////////////////////////////////////////////////////////////////
		public ArrayList<Integer> linear_search(String sort_key,String search_key) {
			
			ArrayList<Integer> linear_al = new ArrayList<Integer>();
			int bool = 1;
			for(int i=0 ;i<al.length;i++) {
				//System.out.println("Searching..."+i);
				switch (sort_key) {
				case "Coupon Provider":{
					bool = search_key.compareToIgnoreCase(al[i].a.getCoupon_site());
					break;
				}
				case "Product Name":{
					
					bool = search_key.compareToIgnoreCase(al[i].a.getProduct_name());
					break;
				}
				case "Price":{
					if(Double.parseDouble(search_key) < al[i].a.getProduct_price())
						bool = -1;
					else if(Double.parseDouble(search_key) == al[i].a.getProduct_price())
						bool = 0;
					else
						bool = 1;
					break;
				}	
				case "Discount Rate":{
					if(Double.parseDouble(search_key) < al[i].a.getDiscount_rate())
						bool = -1;
					else if(Double.parseDouble(search_key) == al[i].a.getDiscount_rate())
						bool = 0;
					else
						bool = 1;
					break;
				}	
				case "Expiration":{
					if(Integer.parseInt(search_key) < al[i].a.getExp_period())
						bool = -1;
					else if(Integer.parseInt(search_key) == al[i].a.getExp_period())
						bool = 0;
					else
						bool = 1;
					break;
				}
				case "Redeemed/Unused":{
					bool = search_key.compareToIgnoreCase(al[i].a.getStatus_of_coupon());
					break;
				}
				case "finalprice":{

					double var1 = al[i].final_price;
					if(Double.parseDouble(search_key) < var1)
						bool = -1;
					else if(Double.parseDouble(search_key) == var1)
						bool = 0;
					else
						bool = 1;
					break;
				}
				}
				if(bool==0) {
					linear_al.add(i+1);
				}
				
			}
			/*
			if(!linear_al.isEmpty()) {
				System.out.println("By Linear search it took "+linear_al.get(0)+" comparison");
				System.out.println("Found "+linear_al.size()+" match");
				int i = 0;
				while(i<linear_al.size()) {
					System.out.println("Found "+al[linear_al.get(i)-1]);
					i++;
				}
			}
			else {
				System.out.println("Not Found");
				System.out.println("By Linear search it took "+al.length+" comparison");
			}*/
			
			return linear_al; 
		}
		
		///////////////////////////////////////////////////////////////////////
		//Binary search algorithm
		///////////////////////////////////////////////////////////////////////
		public ArrayList<Integer> binary_search(int num_search,String sort_key,String search_key) {
			ArrayList<Integer> binary_al = new ArrayList<Integer>();
			int bool_binarysearch = 1;
			int binary_search_count = 1;
			int first_index = 0;
			int last_index = al.length-1;
			int start_index = -1;
			int midpoint=0;
			while(true) {
				//System.out.println("Searching...");
				
				midpoint = (first_index+last_index)/2;
				
				//System.out.println("first index value : "+first_index +"Last Index Value : "+last_index+ " Midpoint value : "+midpoint);
				
				switch (sort_key) {
					case "Coupon Provider":{
						bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint].a.getCoupon_site());
						break;
					}
					case "Product Name":{
						
						bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint].a.getProduct_name());
						break;
					}
					case "Price":{
						if(Double.parseDouble(search_key) < al[midpoint].a.getProduct_price())
							bool_binarysearch = -1;
						else if(Double.parseDouble(search_key) == al[midpoint].a.getProduct_price())
							bool_binarysearch = 0;
						else
							bool_binarysearch = 1;
						break;
					}	
					case "Discount Rate":{
						if(Double.parseDouble(search_key) < al[midpoint].a.getDiscount_rate())
							bool_binarysearch = -1;
						else if(Double.parseDouble(search_key) == al[midpoint].a.getDiscount_rate())
							bool_binarysearch = 0;
						else
							bool_binarysearch = 1;
						break;
					}	
					case "Expiration":{
						if(Integer.parseInt(search_key) < al[midpoint].a.getExp_period())
							bool_binarysearch = -1;
						else if(Integer.parseInt(search_key) == al[midpoint].a.getExp_period())
							bool_binarysearch = 0;
						else
							bool_binarysearch = 1;
						break;
					}
					case "Redeemed/Unused":{
						bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint].a.getStatus_of_coupon());
						break;
					}
					case "finalprice":{
	
						double var1 = al[midpoint].final_price;
						if(Double.parseDouble(search_key) < var1)
							bool_binarysearch = -1;
						else if(Double.parseDouble(search_key) == var1)
							bool_binarysearch = 0;
						else
							bool_binarysearch = 1;
						break;
					}
				}

				//System.out.println("Bool value : "+bool_binarysearch);
				if(bool_binarysearch == 0) {
					if(midpoint!=0) {
						switch (sort_key) {
						case "Coupon Provider":{
							bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint-1].a.getCoupon_site());
							break;
						}
						case "Product Name":{
							
							bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint-1].a.getProduct_name());
							break;
						}
						case "Price":{
							if(Double.parseDouble(search_key) < al[midpoint-1].a.getProduct_price())
								bool_binarysearch = -1;
							else if(Double.parseDouble(search_key) == al[midpoint-1].a.getProduct_price())
								bool_binarysearch = 0;
							else
								bool_binarysearch = 1;
							break;
						}	
						case "Discount Rate":{
							if(Double.parseDouble(search_key) < al[midpoint-1].a.getDiscount_rate())
								bool_binarysearch = -1;
							else if(Double.parseDouble(search_key) == al[midpoint-1].a.getDiscount_rate())
								bool_binarysearch = 0;
							else
								bool_binarysearch = 1;
							break;
						}	
						case "Expiration":{
							if(Integer.parseInt(search_key) < al[midpoint-1].a.getExp_period())
								bool_binarysearch = -1;
							else if(Integer.parseInt(search_key) == al[midpoint-1].a.getExp_period())
								bool_binarysearch = 0;
							else
								bool_binarysearch = 1;
							break;
						}
						case "Redeemed/Unused":{
							bool_binarysearch = search_key.compareToIgnoreCase(al[midpoint-1].a.getStatus_of_coupon());
							break;
						}
						case "finalprice":{
		
							double var1 = al[midpoint-1].final_price;
							if(Double.parseDouble(search_key) < var1)
								bool_binarysearch = -1;
							else if(Double.parseDouble(search_key) == var1)
								bool_binarysearch = 0;
							else
								bool_binarysearch = 1;
							break;
						}
					}
						//System.out.println("next bool value is"+bool_binarysearch);
						if(bool_binarysearch==0) {
							last_index = midpoint-1;
						}
						else {
							start_index = midpoint;
							break;
						}
					}
					else {
						start_index = midpoint;
						break;
					}
				}
				else if(bool_binarysearch < 0) {
					last_index = midpoint-1;
				}
				else if(bool_binarysearch > 0) {
					first_index = midpoint+1;
				}
				//System.out.println("start index : "+start_index);
				if(first_index>last_index) {
					//System.out.println("Not Found");
					break;
				}
				binary_search_count++;
			}
			//System.out.println("binary_search_count : "+binary_search_count +"  "+"start_index : "+start_index);
			binary_al.add(binary_search_count);
			binary_al.add(start_index);
			/*
			if(start_index != -1) {
				System.out.println("By Binary search it took "+binary_search_count+" comparison ");
				System.out.println("Found "+linear_al.size()+" match");
				int i = 0;
				while(i<linear_al.size()) {
					System.out.println("Found "+al[i+midpoint]);
					i++;
				}
			}	
			else {
				System.out.println("By Binary search it took "+binary_search_count+" comparison ");
			}
			*/
			return binary_al;
		}
		
		
		///////////////////////////////////////////////////////////////////////
		//Group sorting algorithm   (Based on number of parameters)
		///////////////////////////////////////////////////////////////////////
		public void group_sort(ArrayList<String> grp_para) {
			

			//System.out.println("Inside grp sort");
			int bool = 0;
			Coupon_FinalPrice C;
			
			for(int k=0;k<grp_para.size();k++)
			{	
			
				for(int i=0; i<al.length-1;i++) {
					for(int j=0;j<al.length-1-i;j++) {
						switch(k+1) {
							case 1:{
								bool = search(grp_para.get(k),j,al);
								break;
							}
							case 2:{
								if(search(grp_para.get(k-1),j,al)==0)
								bool = search(grp_para.get(k),j,al);
								else
								bool = 1;
								break;
							}
							case 3:{
								if(search(grp_para.get(k-1),j,al)==0 && search(grp_para.get(k-2),j,al)==0)
								bool = search(grp_para.get(k),j,al);
								else
								bool = 1;
								break;
							}	
							//Add more case for 4 5 6 and 7 later ....
						}
						
						if (bool < 0) {
						C = al[j];
						al[j] = al[j+1];
						al[j+1] = C;
						}
					
					}
				}
				/*System.out.println("Grp Sorted output for value of k : "+k);
				for(Coupon_FinalPrice x:al)
				System.out.println(x.a+ ",["+x.final_price+"]");*/
			}
			
			/*System.out.println("--------------------------------------\n");
			System.out.println("Grp Sorted output");
			for(Coupon_FinalPrice x:al)
			System.out.println(x.a+ ",["+x.final_price+"]");
			System.out.println("Outside grp sort");*/
		}
		
		/////////////////////////////////////////////////////////////////////////////
		//Compares data based on the sort_key. This method is used to sort the list
		////////////////////////////////////////////////////////////////////////////
		static public int search(String sort_key,int j,Coupon_FinalPrice alcopy[]) {
			int bool = 1;
			switch (sort_key) {
				case "Coupon Provider":{
					//bool = al[j+1].getName().compareTo(al[j].getName());
					bool = alcopy[j+1].a.getCoupon_site().compareToIgnoreCase(alcopy[j].a.getCoupon_site());
					break;
				}
				case "Product Name":{
					//System.out.println("count of j : "+j);
					String y = alcopy[j].a.getProduct_name();
					String x= alcopy[j+1].a.getProduct_name();
					//System.out.println("x : "+x+" , y : "+y);
					bool = x.compareToIgnoreCase(y);
					break;
				}
				case "Price":{
					if(alcopy[j+1].a.getProduct_price() < alcopy[j].a.getProduct_price())
						bool = -1;
					else
						bool = 1;
					break;
				}	
				case "Discount Rate":{
					if(alcopy[j+1].a.getDiscount_rate() < alcopy[j].a.getDiscount_rate())
						bool = -1;
					else
						bool = 1;
					break;
				}	
				case "Expiration":{
					int y = alcopy[j].a.getExp_period();
					int x= alcopy[j+1].a.getExp_period();
					//System.out.println("x : "+x+" , y : "+y);
					if(alcopy[j+1].a.getExp_period() < alcopy[j].a.getExp_period())
						bool = -1;
					else
						bool = 1;
					break;
				}
				case "Redeemed/Unused":{
					bool = alcopy[j+1].a.getStatus_of_coupon().compareToIgnoreCase(alcopy[j].a.getStatus_of_coupon());
					break;
				}
				case "finalprice":{
					double var1 = alcopy[j+1].final_price;
					double var2 = alcopy[j].final_price;
					if(var1<var2)
						bool = -1;
					else
						bool = 1;
					break;
				}
				
			}
			return bool;
		}

}


////////Struct implementation //////////////////
class Coupon_FinalPrice{
	
	public Coupon a;
	public double final_price;
	
	public Coupon_FinalPrice(Coupon peek, double d) {
		a = peek;
		final_price = d;
	}

	public Coupon getA() {
		return a;
	}

	public void setA(Coupon a) {
		this.a = a;
	}

	public double getFinal_price() {
		return final_price;
	}

	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}
}









