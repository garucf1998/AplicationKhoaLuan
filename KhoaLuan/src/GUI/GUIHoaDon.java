package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Service.BenhNhanService;
import Service.ChiTietDonThuocService;
import Service.HoaDonService;
import Service.LichHenService;
import Service.NhanVienService;
import Service.PhieuDichVuService;
import Service.PhieuKhamService;
import enity.BenhNhan;
import enity.ChiTietDonThuoc;
import enity.DonThuoc;
import enity.HoaDon;
import enity.LichHen;
import enity.NhanVien;
import enity.PhieuDichVu;
import enity.PhieuKhambenh;
import enity.TaiKhoan;

public class GUIHoaDon extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane, Jpanel_1;
	private JTextField txtdiaChiBN;
	private JLabel lbldiaChiBN;
	private JTextField txthoTenBN,txtsdt;
	private JLabel lblmaBN;
	private JButton btnhuy,btnluu;
	
	private List<BenhNhan> listBN;
	
	private BenhNhanService benhnhanservice;
	private HoaDonService hoaDonService;
	private ChiTietDonThuocService chiTietDonThuocService;
	private PhieuKhamService phieuKhamService;
	private PhieuDichVuService phieuDichVuService;
	
	private JComboBox comboBox;
	
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien,mBacSy;
	private BenhNhan mBenhNhan;
	private HoaDon mHoaDon;
	
	private DefaultTableModel datamodel,datamodel1,datamodel2; 
	private JScrollPane scrollPane,scrollPane1,scrollPane2;
	private JTable table,table1,table2;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public GUIHoaDon(TaiKhoan taikhoan,NhanVien nhanvien) {
		this.benhnhanservice=new BenhNhanService();
		this.chiTietDonThuocService = new ChiTietDonThuocService();
		this.hoaDonService=new HoaDonService();
		this.phieuKhamService=new PhieuKhamService();
		this.phieuDichVuService = new PhieuDichVuService();
		this.mHoaDon=new HoaDon();
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		
		setTitle("Qu???n l?? h??a ????n");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("H??a ????n");
		lblHpngBn.setBounds(304, 0, 436, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		Jpanel_1 = new JPanel();
		Jpanel_1.setBounds(28, 59, 1104, 93);
		contentPane.add(Jpanel_1);
		Jpanel_1.setBackground(SystemColor.inactiveCaptionBorder);
		Jpanel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th??ng tin b???nh nh??n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel_1.setLayout(null);
				
		JLabel lblhoTenBN = new JLabel("H??? t??n: ");
		lblhoTenBN.setBounds(33, 60, 86, 20);
		Jpanel_1.add(lblhoTenBN);
		lblhoTenBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
		txthoTenBN = new JTextField();
		txthoTenBN.setBounds(152, 61, 268, 20);
		txthoTenBN.setEnabled(false);
		Jpanel_1.add(txthoTenBN);
		txthoTenBN.setUI(new HintTextFieldUI("					Nh???p h??? t??n b???nh nh??n. VD: Nguy???n V??n B", true, Color.GRAY));
		txthoTenBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txthoTenBN.setColumns(10);
				
		txtdiaChiBN = new JTextField();
		txtdiaChiBN.setBounds(742, 61, 268, 20);
		Jpanel_1.add(txtdiaChiBN);
		txtdiaChiBN.setUI(new HintTextFieldUI("					Nh???p ?????a ch??? kh??ch h??ng. VD: B??nh Thu???n", true, Color.GRAY));
		txtdiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtdiaChiBN.setColumns(10);
		txtdiaChiBN.setEnabled(false);
				
		lbldiaChiBN = new JLabel("?????a ch???:");
		lbldiaChiBN.setBounds(623, 60, 86, 20);
		Jpanel_1.add(lbldiaChiBN);
		lbldiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
		lblmaBN = new JLabel("M?? b???nh nh??n :");
		lblmaBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblmaBN.setBounds(33, 30, 86, 20);
		Jpanel_1.add(lblmaBN);
				
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();       
		String todayAsString = df.format(today);
				
		try {
			 listBN= benhnhanservice.GetAllBenhNhan();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		 comboBox = new JComboBox();
				
		for(int i=0;i<listBN.size();i++)
			comboBox.addItem(listBN.get(i).getId());
		comboBox.setSelectedItem(null);
		comboBox.setBounds(152, 30, 268, 20);
		Jpanel_1.add(comboBox);
				
		JLabel lblsdT = new JLabel("S??? ??i???n tho???i:");
		lblsdT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblsdT.setBounds(623, 30, 86, 20);
		Jpanel_1.add(lblsdT);
				
		txtsdt = new JTextField();
		txtsdt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtsdt.setEnabled(false);
		txtsdt.setColumns(10);
		txtsdt.setBounds(742, 31, 268, 20);
		Jpanel_1.add(txtsdt);
		
		String[]headers = {"M??","Ng??y t???o","T???ng ti???n", "ID phi???u kh??m b???nh","Tr???ng th??i"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(51, 185, 1060, 109);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
						try {
							mBenhNhan=benhnhanservice.GetOneBenhNhan(Long.parseLong(comboBox.getSelectedItem().toString()));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
								txthoTenBN.setText(mBenhNhan.getTen());
								txtdiaChiBN.setText(mBenhNhan.getDiaChi());
								txtsdt.setText(mBenhNhan.getSoDienThoai());
								removeTable();
								updateTableData();
					
			}
		});
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(28, 163, 1104, 147);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Danh s??ch h??a ????n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panel_2);
		
		String[]header = {"M??","T??n thu???c","S??? l?????ng", "H?????ng d???n s??? d???ng","Gi?? ti???n"};
		datamodel1 = new DefaultTableModel(header,0);
		contentPane.add(scrollPane1= new JScrollPane(table1 = new JTable(datamodel1)));
		scrollPane1.setBounds(51, 351, 1060, 98);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane1.setBackground(SystemColor.scrollbar);
		
		String[]header2 = {"M??","Ghi ch??","K???t lu???n","Ng??y t???o","T??n d???ch vu","Gi?? ti???n"};
		datamodel2 = new DefaultTableModel(header2,0);
		contentPane.add(scrollPane2= new JScrollPane(table2 = new JTable(datamodel2)));
		scrollPane2.setBounds(51, 461, 1060, 98);
		table2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane2.setBackground(SystemColor.scrollbar);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Chi ti???t c??c kho???n thu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2_1.setBounds(28, 320, 1104, 254);
		contentPane.add(panel_2_1);
		
		btnhuy = new JButton("Quay L???i");
		btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnhuy.setBounds(977, 597, 155, 57);
		contentPane.add(btnhuy);
		
		btnluu = new JButton("Thanh To??n");
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnluu.setBounds(752, 597, 155, 57);
		contentPane.add(btnluu);
		
		comboBox.setEditable(true);
		table.addMouseListener(this);
		btnhuy.addActionListener(this);
		btnluu.addActionListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		PhieuKhambenh pkb= new PhieuKhambenh();
		
		removeTable1();
		removeTable2();
		
		try {
			pkb = phieuKhamService.GetOnePhieuKham(Long.parseLong(table.getValueAt(row, 3).toString()));
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(pkb!=null) {
			ArrayList<ChiTietDonThuoc>list=new ArrayList<>();
			try {
				list.addAll(chiTietDonThuocService.GetAllChiTietDonThuocByDonThuoc(pkb.getDonthuoc().getId()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(list.size()!=0) {
				for (ChiTietDonThuoc pk : list) {
					
					String[] rowdata = { String.valueOf(pk.getId()),pk.getThuoc().getTenThuoc(),String.valueOf(pk.getSoLuong()),pk.getGhiChu(),String.valueOf(pk.getGiaTien())};
					datamodel1.addRow(rowdata);
				}
			}
		}
		if(pkb!=null) {
			ArrayList<PhieuDichVu>list1=new ArrayList<>();
			try {
				list1.addAll(phieuDichVuService.GetAllDichVuByPhieuKham(pkb.getId()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(list1.size()!=0) {
				for (PhieuDichVu pk : list1) {
					
					String[] rowdata = { String.valueOf(pk.getId()),pk.getGhiChu(),pk.getKetLuan(),benhnhanservice.doichuoitungay(pk.getNgayTaoPhieu()),pk.getDichVu().getGhiChu(),String.valueOf(pk.getGiaTienDV())};
					datamodel2.addRow(rowdata);
				}
			}
		}
		
		try {
			mHoaDon=hoaDonService.GetOneHoaDon(Long.parseLong(table.getValueAt(row, 0).toString()));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		int ketquaPUT=0;
		if(o==btnhuy)
		{
			dispose();
			GUIChucNang cn= new GUIChucNang(mTaiKhoan, mNhanVien);
			cn.setVisible(true);
		}else if(o==btnluu) {
			mHoaDon.setTrangThai(true);
			try {
				 ketquaPUT = hoaDonService.PUTHoaDon(mHoaDon);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaPUT==200)
			{
				JOptionPane.showMessageDialog(table,"B???n v???a thanh to??n 1 h??a ????n !","Ch?? ??",JOptionPane.CLOSED_OPTION);
				removeTable();
				removeTable1();
				removeTable2();
				dispose();
				GUIChucNang cn= new GUIChucNang(mTaiKhoan, mNhanVien);
				cn.setVisible(true);
			}
		}
	}
	public void updateTableData() 
	{
		// TODO Auto-generated method stub
		ArrayList<HoaDon>list=new ArrayList<>();
		try {
			list.addAll(hoaDonService.GetAllHoaDonChuaThanhToan((Long) comboBox.getSelectedItem()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()!=0)
		{
			
			for (HoaDon hd : list) {
				String trangthai="";
				if(hd.isTrangThai())
					trangthai="???? Thanh To??n";
				else
					trangthai="Ch??a Thanh To??n";
				String[] rowdata = { String.valueOf(hd.getId()),benhnhanservice.doichuoitungay(hd.getNgayTao()),String.valueOf(hd.getTongTien()),String.valueOf(hd.getPhieukhambenh().getId()),trangthai};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
	public void removeTable1() {
		DefaultTableModel tableModel1 = (DefaultTableModel) table1.getModel();
		tableModel1.setRowCount(0);
	}
	public void removeTable2() {
		DefaultTableModel tableModel2 = (DefaultTableModel) table2.getModel();
		tableModel2.setRowCount(0);
	}
}
