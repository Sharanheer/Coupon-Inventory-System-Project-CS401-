package couponInventorySystem.commons;


public class ArrayLinkedList {
	
		protected ArrayLLNode[] head;
		int numElements;
		public int defaultSize = 10;
		
		public ArrayLinkedList() {
			
			head = new ArrayLLNode [defaultSize];
			numElements = 0;
			
		}
		
		public boolean add(Coupon e) {
			
			ArrayLLNode newNode =  new ArrayLLNode(e);
			int index = 0;
				if(head[0]!= null) {
					while((head[index].getLink()) != -1) 
						index = head[index].getLink();
					head[index+1] = newNode;
					head[index].setLink(index+1);
					
				}
				else
					head[0] = newNode;
				//System.out.println("what is the problem while adding coupons "+head[index].getData()+","+head[index].getLink());
				numElements++;
				
				return true;
		}

		public boolean is_full() {
			// TODO Auto-generated method stub
			return numElements == head.length;
		}

		public boolean is_empty() {
			// TODO Auto-generated method stub
			return (numElements == 0);
		}

		public int size() {
			// TODO Auto-generated method stub
			return numElements;
		}

		public void print() {
			//location = head;
			int index = 0;
			while(index != -1 && head[index]!=null) {
				System.out.println(head[index].getData());
				index = head[index].getLink();
			}
		}

		public ArrayLLNode[] getHead() {
			return head;
		}

		public void setHead(ArrayLLNode[] head) {
			this.head = head;
		}
		
		public void enlarge()
		// Increments the capacity of the array by double the default capacity.
		{
		// create the larger array
			ArrayLLNode[] larger = new ArrayLLNode[defaultSize*2];
		// copy the contents from the smaller array into the larger array 
			int currSmaller = 0;
			for (int currLarger = 0; currLarger < numElements; currLarger++) {
				larger[currLarger] = head[currSmaller];
				currSmaller++; 
			}
			head = larger;
		}
		
		
	}
