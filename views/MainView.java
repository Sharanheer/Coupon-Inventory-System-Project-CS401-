package couponInventorySystem.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import couponInventorySystem.commons.ArrayLinkedList;
import couponInventorySystem.commons.Coupon;

import couponInventorySystem.commons.ReadFile;
import couponInventorySystem.commons.Search;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Button;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Component;

public class MainView extends JFrame {

	
	private ArrayLinkedList list1 = new ArrayLinkedList();
	private File dstFile;
	private String temp;
	private JPanel contentPane;
	private CardLayout cl1;
	
	private JPanel MenuP;
	private JPanel WelcomeP;
	private JPanel AddP1_Manual;
	
	private JButton btnListCoupons;
	private JButton btnFindCoupons;
	private JButton btnAddNewCoupon;
	
	private JButton btnManual_Cancel;
	private JButton btnManual_Proceed;
	private JTextField txtEnterCouponProvider;
	private JTextField txtEnterProductName;
	private JTextField txtEnterPriceOf;
	private JTextField txtEnterDiscountFor;
	private JTextField txtEnterExpirationPeriod;
	
	private JLabel lblIntro;
	private JLabel lblToProceedPlease;
	private JButton btnBrowse;
	private JRadioButton rdbtnRedeemed;
	private JRadioButton rdbtnUnused;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel SearchP;
	private JTextField tfSearch_Value;
	private JPanel SearchP1;
	private JButton btnSearch_SearchP;
	private JButton btnCancel_SearchP;
	private JComboBox CBSearchPara;
	private JPanel ListP;
	private JButton btnSortCoupons;
	private JTable List_Table;
	private JCheckBox chkbCS;
	private JCheckBox chkbFP;
	private JCheckBox chkbEP;
	private JCheckBox chkbCP;
	private JCheckBox chkbPN;
	private JCheckBox chkbP;
	private JCheckBox chkbDR;
	private JButton btnCancel;
	private JTable search_table;
	private JTextArea tasearch_result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				
					frame.temp = "src/couponInventorySystem/resources/file_name.txt";
					frame.dstFile = new File(frame.temp);
					if(!frame.dstFile.exists()) {
						frame.cl1.show(frame.contentPane, "Welcome");
					}
					//create linked list and set MenuP visible
					else{
						frame.createLinkedList();
					}
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "File not found Error "+e);
				}
				catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Some Error "+e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() 
	{
		initComponents();
		createEvents();
		
	}
	
	
	/**
	 * 
	 * Creates linked list by reading the coupons from the file
	 */
	public void createLinkedList() throws FileNotFoundException {
		// TODO Auto-generated method stub
		ReadFile rd = new ReadFile();
		rd.openFile(temp);
		while(rd.hasData()) {
			if(!list1.is_full())
				list1.add(rd.readFile());
			else {
				JOptionPane.showMessageDialog(null, "Default size of the array reached. Enlarging the array and proceed");
			    list1.enlarge();
				list1.add(rd.readFile());
			}
		}	
		cl1.show(contentPane, "Menu");
	}
	

	/////////////////////////////////////////////////////////////////////////////////
	//This method contains all of the code for creating and initializing the components
	/////////////////////////////////////////////////////////////////////////////////
	private void initComponents()
	{
		setTitle("Coupon Inventory System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 750, 450);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cl1 =  new CardLayout();
		contentPane.setLayout(cl1);
		cl1.show(contentPane, "Welcome");
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		//WelcomeP to allow user to choose the file to enter the coupon details
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		WelcomeP = new JPanel();
		contentPane.add(WelcomeP, "Welcome");
		
		lblIntro = new JLabel("Welcome to the Coupon Inventory System");
		lblIntro.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIntro.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblToProceedPlease = new JLabel("To proceed please select a coupon data file ");
		lblToProceedPlease.setHorizontalTextPosition(SwingConstants.CENTER);
		lblToProceedPlease.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setHorizontalTextPosition(SwingConstants.CENTER);
		
		GroupLayout gl_WelcomeP = new GroupLayout(WelcomeP);
		gl_WelcomeP.setHorizontalGroup(
			gl_WelcomeP.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_WelcomeP.createSequentialGroup()
					.addGap(82)
					.addComponent(lblIntro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(96))
				.addGroup(Alignment.LEADING, gl_WelcomeP.createSequentialGroup()
					.addGap(41)
					.addComponent(lblToProceedPlease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(47))
				.addGroup(Alignment.LEADING, gl_WelcomeP.createSequentialGroup()
					.addGap(169)
					.addComponent(btnBrowse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(183))
		);
		gl_WelcomeP.setVerticalGroup(
			gl_WelcomeP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WelcomeP.createSequentialGroup()
					.addGap(42)
					.addComponent(lblIntro)
					.addGap(27)
					.addComponent(lblToProceedPlease)
					.addGap(71)
					.addComponent(btnBrowse)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		WelcomeP.setLayout(gl_WelcomeP);
		
		///////////////////////////////////////////////////////////////////////
		//MenuP gives users 3 options: Add,search and list using three buttons
		////////////////////////////////////////////////////////////////////////
		MenuP = new JPanel();
		contentPane.add(MenuP, "Menu");
		MenuP.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//MenuP.setVisible(false);
		
		btnAddNewCoupon = new JButton("Add New Coupon");
		btnAddNewCoupon.setBackground(Color.WHITE);
		MenuP.add(btnAddNewCoupon);
		
		btnFindCoupons = new JButton("Search Coupons");
		
		btnFindCoupons.setBackground(Color.WHITE);
		MenuP.add(btnFindCoupons);
		
		btnListCoupons = new JButton("List Coupons");
		
		MenuP.add(btnListCoupons);
		btnListCoupons.setBackground(Color.WHITE);
		
		//////////////////////////////////////////////////////////////
		//AddP1_Manual to allow user to add coupon manually
		////////////////////////////////////////////////////////////
		AddP1_Manual = new JPanel();
		contentPane.add(AddP1_Manual, "Manual");
		//AddP1_Manual.setVisible(false);
		
		btnManual_Cancel = new JButton("Cancel");
		btnManual_Proceed = new JButton("Proceed");
		
		
		//Panel for Labels and TextFields //
		JPanel ManualAdd_ScrollPanel = new JPanel();
		GroupLayout gl_AddP1_Manual = new GroupLayout(AddP1_Manual);
		gl_AddP1_Manual.setHorizontalGroup(
			gl_AddP1_Manual.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddP1_Manual.createSequentialGroup()
					.addContainerGap()
					.addComponent(ManualAdd_ScrollPanel, GroupLayout.PREFERRED_SIZE, 615, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_AddP1_Manual.createSequentialGroup()
					.addGap(416)
					.addComponent(btnManual_Proceed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnManual_Cancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(33))
		);
		gl_AddP1_Manual.setVerticalGroup(
			gl_AddP1_Manual.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_AddP1_Manual.createSequentialGroup()
					.addContainerGap()
					.addComponent(ManualAdd_ScrollPanel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addGroup(gl_AddP1_Manual.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnManual_Cancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnManual_Proceed))
					.addGap(54))
		);
		
		JLabel lblCouponSite = new JLabel("Coupon Site :");
		
		JLabel lblProductName = new JLabel("Product name :");
		
		JLabel lblOriginalPrice = new JLabel("Original Price :");
		
		JLabel lblDiscountRate = new JLabel("Discount Rate :");
		
		JLabel lblExpirationPeriod = new JLabel("Expiration Period :");
		
		JLabel lblStatusOfCoupon = new JLabel("Status of Coupon :");
		
		txtEnterCouponProvider = new JTextField();
		txtEnterCouponProvider.setToolTipText("Enter Provider Name");
		txtEnterCouponProvider.setColumns(10);
		
		
		txtEnterProductName = new JTextField();
		txtEnterProductName.setColumns(10);
		
		txtEnterPriceOf = new JTextField();
		txtEnterPriceOf.setColumns(10);
		
		txtEnterDiscountFor = new JTextField();
		txtEnterDiscountFor.setColumns(10);
		
		txtEnterExpirationPeriod = new JTextField();
		txtEnterExpirationPeriod.setColumns(10);
		
		rdbtnUnused = new JRadioButton("Unused");
		rdbtnUnused.setSelected(true);
		buttonGroup.add(rdbtnUnused);
		
		rdbtnRedeemed = new JRadioButton("Redeemed");
		buttonGroup.add(rdbtnRedeemed);
		
		JLabel lblMustBeAlphabets_1 = new JLabel("Must be alphabets & limited to 20 char");
		
		JLabel lblMustBeAlphabets = new JLabel("Must be alphabets & limited to 10 char");
		
		JLabel lblMustBeA = new JLabel("Must be a positive value ");
		
		JLabel lblMustBeA_1 = new JLabel("Must be a positive value");
		
		JLabel lblMustBeIn = new JLabel("Must be number of days between (1-365)");
		
		
		GroupLayout gl_ManualAdd_ScrollPanel = new GroupLayout(ManualAdd_ScrollPanel);
		gl_ManualAdd_ScrollPanel.setHorizontalGroup(
			gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ManualAdd_ScrollPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCouponSite)
						.addComponent(lblProductName)
						.addComponent(lblOriginalPrice)
						.addComponent(lblDiscountRate)
						.addComponent(lblExpirationPeriod)
						.addComponent(lblStatusOfCoupon))
					.addGap(29)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ManualAdd_ScrollPanel.createSequentialGroup()
							.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEnterExpirationPeriod, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(txtEnterPriceOf, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(txtEnterProductName, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addComponent(txtEnterDiscountFor, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
								.addGroup(gl_ManualAdd_ScrollPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEnterCouponProvider, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMustBeAlphabets_1)
								.addComponent(lblMustBeAlphabets)
								.addComponent(lblMustBeA)
								.addComponent(lblMustBeA_1)
								.addComponent(lblMustBeIn))
							.addGap(133))
						.addGroup(gl_ManualAdd_ScrollPanel.createSequentialGroup()
							.addComponent(rdbtnUnused)
							.addGap(18)
							.addComponent(rdbtnRedeemed)
							.addGap(342)))
					.addGap(141))
		);
		gl_ManualAdd_ScrollPanel.setVerticalGroup(
			gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ManualAdd_ScrollPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCouponSite)
						.addComponent(txtEnterCouponProvider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMustBeAlphabets))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductName)
						.addComponent(txtEnterProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMustBeAlphabets_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOriginalPrice)
						.addComponent(txtEnterPriceOf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMustBeA))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiscountRate)
						.addComponent(txtEnterDiscountFor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMustBeA_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExpirationPeriod)
						.addComponent(txtEnterExpirationPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMustBeIn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ManualAdd_ScrollPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatusOfCoupon)
						.addComponent(rdbtnUnused)
						.addComponent(rdbtnRedeemed))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		ManualAdd_ScrollPanel.setLayout(gl_ManualAdd_ScrollPanel);
		AddP1_Manual.setLayout(gl_AddP1_Manual);
		
		
		//////////////////////////////////////////////////////////////
		//SearchP panel for searching coupons per key
		////////////////////////////////////////////////////////////
		SearchP = new JPanel();
		contentPane.add(SearchP, "Search");
		String [] options = {"None","Coupon Provider","Product Name","Price","Discount Rate","Expiration","Redeemed/Unused"};
		CBSearchPara = new JComboBox(options);
		
		CBSearchPara.setToolTipText("Select the key per which to sort/search");
		
		JLabel lblChooseTheSearch = new JLabel("Choose the search parameter :");
		
		SearchP1 = new JPanel();
		SearchP1.setVisible(false);
		
		btnCancel_SearchP = new JButton("Cancel");
		
		search_table = new JTable();
		search_table.setOpaque(false);
		search_table.setAutoscrolls(false);
		
		tasearch_result = new JTextArea();
		tasearch_result.setOpaque(false);
		
		GroupLayout gl_SearchP = new GroupLayout(SearchP);
		gl_SearchP.setHorizontalGroup(
			gl_SearchP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SearchP.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_SearchP.createParallelGroup(Alignment.TRAILING)
						.addComponent(tasearch_result, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
						.addComponent(search_table, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_SearchP.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_SearchP.createSequentialGroup()
								.addComponent(lblChooseTheSearch)
								.addGap(32)
								.addComponent(CBSearchPara, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(btnCancel_SearchP, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addComponent(SearchP1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(7))
		);
		gl_SearchP.setVerticalGroup(
			gl_SearchP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SearchP.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_SearchP.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChooseTheSearch)
						.addComponent(CBSearchPara, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel_SearchP, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SearchP1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tasearch_result, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(search_table, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		tfSearch_Value = new JTextField();
		tfSearch_Value.setToolTipText("Enter the value to be searched");
		tfSearch_Value.setColumns(10);
		
		JLabel lblEnterTheSearch = new JLabel("Enter the search value :");
		
		btnSearch_SearchP = new JButton("Search");
		
		
		GroupLayout gl_SearchP1 = new GroupLayout(SearchP1);
		gl_SearchP1.setHorizontalGroup(
			gl_SearchP1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SearchP1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnterTheSearch)
					.addGap(75)
					.addComponent(tfSearch_Value, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnSearch_SearchP, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		gl_SearchP1.setVerticalGroup(
			gl_SearchP1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SearchP1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_SearchP1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterTheSearch)
						.addComponent(tfSearch_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch_SearchP))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		SearchP1.setLayout(gl_SearchP1);
		SearchP.setLayout(gl_SearchP);
		
		//////////////////////////////////////////////////////////////
		//ListP panel for Listing coupons per keys
		////////////////////////////////////////////////////////////
		ListP = new JPanel();
		contentPane.add(ListP, "list");
		
		chkbCP = new JCheckBox("Coupon Provider");
		
		chkbPN = new JCheckBox("Product Name");
		
		chkbP = new JCheckBox("Price");
		
		chkbCS = new JCheckBox("Coupon Status");
		
		chkbEP = new JCheckBox("Expiration Period");
		
		chkbFP = new JCheckBox("Final Price");
		
		chkbDR = new JCheckBox("Discount rate");
		
		btnSortCoupons = new JButton("Sort Coupons");
		
		String [] column_name = { "Coupon Provider", "Product Name", "Price",
	            "Discount Rate", "Expiration", "Redeemed/Unused", "finalprice" };
		List_Table = new JTable();
		List_Table.setOpaque(false);
		
		
		btnCancel = new JButton("Cancel");
		
		JLabel lblSortCouponsAs = new JLabel("Sort coupons as per below choice");
		
		GroupLayout gl_ListP = new GroupLayout(ListP);
		gl_ListP.setHorizontalGroup(
			gl_ListP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListP.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ListP.createSequentialGroup()
							.addComponent(chkbCP, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(chkbEP, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
					.addGap(37)
					.addGroup(gl_ListP.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSortCoupons, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
						.addGroup(gl_ListP.createSequentialGroup()
							.addComponent(chkbPN, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
							.addGap(9))
						.addGroup(gl_ListP.createSequentialGroup()
							.addComponent(chkbCS, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
							.addGap(5)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ListP.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ListP.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_ListP.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ListP.createSequentialGroup()
									.addComponent(chkbFP, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
									.addGap(150))
								.addGroup(gl_ListP.createSequentialGroup()
									.addComponent(chkbP, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
									.addGap(67)
									.addComponent(chkbDR, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(gl_ListP.createSequentialGroup()
							.addGap(39)
							.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
							.addGap(142)))
					.addGap(27))
				.addGroup(gl_ListP.createSequentialGroup()
					.addGap(254)
					.addComponent(lblSortCouponsAs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(275))
				.addGroup(gl_ListP.createSequentialGroup()
					.addContainerGap()
					.addComponent(List_Table, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_ListP.setVerticalGroup(
			gl_ListP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListP.createSequentialGroup()
					.addGap(12)
					.addComponent(lblSortCouponsAs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListP.createParallelGroup(Alignment.BASELINE)
						.addComponent(chkbCP)
						.addComponent(chkbPN)
						.addComponent(chkbDR)
						.addComponent(chkbP))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListP.createParallelGroup(Alignment.BASELINE)
						.addComponent(chkbEP)
						.addComponent(chkbCS)
						.addComponent(chkbFP))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListP.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnSortCoupons))
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(List_Table, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		ListP.setLayout(gl_ListP);
		
		
		
		
	}

	/////////////////////////////////////////////////////////////////////////////////
	//This method contains all of the code for creating events
	/////////////////////////////////////////////////////////////////////////////////
	private void createEvents() 
	{
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Below btnBrowse ActionListener allows user to select a file from the computer and creates a copy of the file in the resource folder
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println("Browser output is "+selectedFile.getAbsolutePath());
					String src_file = selectedFile.getAbsolutePath();
					 
					try {
						 FileChannel src = new FileInputStream(new File(src_file)).getChannel();
						 FileChannel dst = new FileOutputStream(dstFile).getChannel();
						 if(!dstFile.exists()) {
							 dstFile.createNewFile();
						 }
						 int count = 0;
						 while(count < src.size()) {
							 count+= dst.transferFrom(src, 0, src.size() - count);
						 }
						 src.close();
						 dst.close();
						 JOptionPane.showMessageDialog(null, "File was loaded successfully");
						 createLinkedList();   
						    
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				   
				}
			}
		});
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//On clicking addnewcoupon the below function allows user to enter the details of the new coupon
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		btnAddNewCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtEnterCouponProvider.setToolTipText("Enter the Coupon Provider Name. Max lengh 10 characters");
				txtEnterProductName.setToolTipText("Enter the Product name. Max 20 characters");
				txtEnterPriceOf.setToolTipText("Enter Price of the Product");
				txtEnterDiscountFor.setToolTipText("Enter Discount for the Product");
				txtEnterExpirationPeriod.setToolTipText("Enter Expiration Period in Days");
				//txtEnterTheCurrent.setToolTipText("What's the current status of the Coupon");
				//AddP1_Manual.setVisible(true);
				//MenuP.setVisible(false);
				//WelcomeP.setVisible(false);
				cl1.show(contentPane, "Manual");
			}
		});
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//It validates the user input and add the coupon to the file as well as the main list
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		btnManual_Proceed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Create node//
				//list1 = new LinkedList<Coupon>();
				//String provider_name = txtEnterCouponProvider.getText();
				Coupon c1 = new Coupon();
				
				if(!validation("Coupon Provider",txtEnterCouponProvider.getText()))
					JOptionPane.showMessageDialog(null, "Please check the Coupon Site field, it seems there is some problem");
				else if(!validation("Product Name",txtEnterProductName.getText()))
					JOptionPane.showMessageDialog(null, "Please check the Product Name field, it seems there is some problem");
				else if(!validation("Price",txtEnterPriceOf.getText()))
					JOptionPane.showMessageDialog(null, "Please check the Price field, it seems there is some problem");
				else if(!validation("Discount Rate",txtEnterDiscountFor.getText()))
					JOptionPane.showMessageDialog(null, "Please check the Coupon Provider field, it seems there is some problem");	
				else if(!validation("Expiration",txtEnterExpirationPeriod.getText()))
					JOptionPane.showMessageDialog(null, "Please check the Coupon Provider field, it seems there is some problem");
				else {
					c1.setCoupon_site(txtEnterCouponProvider.getText());
					c1.setProduct_name(txtEnterProductName.getText());
					c1.setProduct_price(Double.parseDouble(txtEnterPriceOf.getText()));
					c1.setDiscount_rate(Double.parseDouble(txtEnterDiscountFor.getText()));
					c1.setExp_period(Integer.parseInt(txtEnterExpirationPeriod.getText()));
					if(rdbtnUnused.isSelected())
						c1.setStatus_of_coupon("Unused");
					else if(rdbtnRedeemed.isSelected()){
						c1.setStatus_of_coupon("Redeemed");
					}
					boolean validate = false;
					if(!list1.is_full()) {
						validate = list1.add(c1);
					}
					else {
						JOptionPane.showMessageDialog(null, "Array is full. Enlarging the default array ...");
						list1.enlarge();
						validate = list1.add(c1);
					}
					try {
						 
						//LinkedList<Coupon> location = new LinkedList<Coupon>();
						//location.setHead(list1.getHead());
						Formatter x = new Formatter(temp);
						int index = 0;
						while(index != -1 && list1.getHead()[index]!=null) {
							
							x.format("%s%s%s%s%s%s\n", list1.getHead()[index].getData().getCoupon_site()+" " ,
									list1.getHead()[index].getData().getProduct_name()+" " ,
									String.valueOf(list1.getHead()[index].getData().getProduct_price())+" " ,
									String.valueOf(list1.getHead()[index].getData().getDiscount_rate())+" " ,
									String.valueOf(list1.getHead()[index].getData().getExp_period())+" " ,
									list1.getHead()[index].getData().getStatus_of_coupon());
							index = list1.getHead()[index].getLink();
						}
						x.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error file could not be located "+e);
						
					}						
				
					if(validate)
						JOptionPane.showMessageDialog(null, "Coupon has been entered into the inventory");
					else
						JOptionPane.showMessageDialog(null, "Something went wrong... ");
					cl1.show(contentPane, "Menu");
					
				}
			}
		});
		
		
		btnManual_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl1.show(contentPane, "Menu");
				//AddP1_Manual.setVisible(false);
				//MenuP.setVisible(true);
			}
		});
	
		btnFindCoupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CBSearchPara.setSelectedIndex(0);
	
				cl1.show(contentPane, "Search");
			}
		});
		
		btnCancel_SearchP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl1.show(contentPane, "Menu");
			}
		});
		
		CBSearchPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) CBSearchPara.getSelectedItem();
				if(selected.equals("None")) {
					SearchP1.setVisible(false);
					JOptionPane.showMessageDialog(null, "Please select a key/parameter per which to search");
				}
				else
					SearchP1.setVisible(true);
					
			}
		});
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Searches coupon based on the user input....
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		btnSearch_SearchP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String search_value = tfSearch_Value.getText();
				String sort_key = (String) CBSearchPara.getSelectedItem();
				
				boolean bool = validation(sort_key,search_value);
				if(bool) {
					
					Search al = new Search(list1.size());
					al.copyLinkedToArray(list1);
					//al.print();
					al.sort(sort_key);
					ArrayList<Integer> linear_al = al.linear_search(sort_key, search_value);
					ArrayList<Integer> binary_al = al.binary_search(linear_al.size(), sort_key, search_value);
					DefaultTableModel dtm;
					String result="";
					if(!linear_al.isEmpty() && !binary_al.isEmpty()) {
						
						result+="Found "+linear_al.size()+" match \n";
			
						result+="By Linear search it took "+linear_al.get(linear_al.size()-1)+" comparison and by Binary search it "+ 
									"took"+binary_al.get(0)+" comparison";
						
						///////////////Printing result in the table//////////////////////
						
						dtm = new DefaultTableModel(0, 0);
						String header[] = new String[] { "Coupon Provider", "Product Name", "Price",
					            "Discount Rate", "Expiration", "Redeemed/Unused", "finalprice" };
						// add header in table model     
						 dtm.setColumnIdentifiers(header);
						
						//set model into the table object
						 search_table.setModel(dtm);

						// add row dynamically into the table   
						 dtm.addRow(new Object[] { "Coupon Provider", "Product Name", "Price",
					        		"Discount Rate", "Expiration", "Redeemed/Unused" ,"finalprice"});
						 dtm.addRow(new Object[] { "--------------", "-------------", "---------",
					        		"-----------", "---------", "---------" ,"-----------"});
						for (int count = 0; count < linear_al.size(); count++) {
						        dtm.addRow(new Object[] { al.output1(linear_al.get(count)-1)[0], al.output1(linear_al.get(count)-1)[1], al.output1(linear_al.get(count)-1)[2],
						        		al.output1(linear_al.get(count)-1)[3], al.output1(linear_al.get(count)-1)[4], al.output1(linear_al.get(count)-1)[5] ,al.output1(linear_al.get(count)-1)[6]});
						 }
					}
					else {
						result+="No Match Found \n By Linear search it took "+list1.size()+" comparison and by Binary search it took "+binary_al.get(0)+
								" comparison";
						dtm = new DefaultTableModel(0, 0);
						search_table.setModel(dtm);
						dtm.addRow(new Object[] {});
					}
					tasearch_result.setText(result);
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid search value");
				}
					
			}
		});
		
		//listP cancel button to return to the MenuP
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl1.show(contentPane, "Menu");
			}
		});
		
		btnListCoupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl1.show(contentPane, "list");
			}
		});
		
		/////////////////////GRP SORTING BUTTON //////////////////////
		btnSortCoupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> grp_para = new ArrayList<String>();
				if(chkbCP.isSelected()) 
					grp_para.add("Coupon Provider");
				if(chkbPN.isSelected())
					grp_para.add("Product Name");
				if(chkbP.isSelected())
					grp_para.add("Price");
				if(chkbDR.isSelected())
					grp_para.add("Discount Rate");
				if(chkbEP.isSelected())
					grp_para.add("Expiration");
				if(chkbCS.isSelected())
					grp_para.add("Redeemed/Unused");
				if(chkbFP.isSelected())
					grp_para.add("finalprice");
				if(grp_para.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please select atleast one of the checkbox before proceeding");
				}
				else {
	
					Search grp_al = new Search(list1.size());
					grp_al.copyLinkedToArray(list1);
					grp_al.group_sort(grp_para);
					
					///////////////Printing result in the table//////////////////////
	
					String header[] = new String[] { "Coupon Provider", "Product Name", "Price",
				            "Discount Rate", "Expiration", "Redeemed/Unused", "finalprice" };
					// add header in table model     
					
					DefaultTableModel dtm = new DefaultTableModel(0, 0);
					
					// add header in table model     
					dtm.setColumnIdentifiers(header);
					//set model into the table object
					List_Table.setModel(dtm);
					 
					// add row into the table   
					 dtm.addRow(new Object[] { "Coupon Provider", "Product Name", "Price",
				        		"Discount Rate", "Expiration", "Redeemed/Unused" ,"finalprice"});
					 dtm.addRow(new Object[] { "--------------", "-------------", "---------",
				        		"-----------", "---------", "---------" ,"-----------"});
					 for (int count = 0; count < list1.size(); count++) {
					        dtm.addRow(new Object[] { grp_al.output1(count)[0], grp_al.output1(count)[1], grp_al.output1(count)[2],
					        		grp_al.output1(count)[3], grp_al.output1(count)[4], grp_al.output1(count)[5] ,grp_al.output1(count)[6]});
					 }
					
				}
				
			}
		});
		
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	///Method to validate the inputs from the user...
	/////////////////////////////////////////////////////////////////////////////////////////
	public boolean validation(String search_para,String search_value) {
		// TODO Auto-generated method stub
		boolean bool = false;
		switch(search_para){
			case "Coupon Provider":{
				if(search_value.length()<=10 && search_value.matches("[a-zA-Z_&]+")){
					bool = true;
				}
				break;		
			}
			case "Product Name":{
				if(search_value.length()<=20 && search_value.matches("[a-zA-Z_&]+")){
					bool = true;
				}
				break;	
			}
			case "Price":{							
				if(search_value.matches("[0-9].+") && !search_value.matches("0+")) {
					bool = true;
				}
				break;
			}
			case "Discount Rate":{
				if(search_value.matches("[0-9]+") && !search_value.matches("0+") && Double.parseDouble(search_value)<=100) {
					bool = true;
				}
				break;
			}
			case "Expiration":{
				if(search_value.matches("[0-9]+") && !search_value.matches("0+") && Integer.parseInt(search_value)<=365) {
					bool = true;
				}
				break;
			}
			case "Redeemed/Unused":{
				if(search_value.compareToIgnoreCase("Unused")==0 || search_value.compareToIgnoreCase("Redeemed")==0 )
					bool = true;
				break;
			}
		}
		return bool;
	}
}



