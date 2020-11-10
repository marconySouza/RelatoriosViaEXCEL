/**
 * @author marcony.souza
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author marcony.souza
 *
 */
public class DashboardView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardView frame = new DashboardView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashboardView() {
		setAutoRequestFocus(false);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setOpacity(1.0f);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DashboardView.class.getResource("/resources/relatorio.png")));
		setTitle("Relatorio de Treinamentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel label = new JLabel("");
		contentPane.add(label, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnInserirTreinamento = new JButton("Inserir");
		btnInserirTreinamento.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnInserirTreinamento.setBounds(346, 114, 90, 28);

		btnInserirTreinamento.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					setVisible(false);
					InsereTreinamentoView insereTreinamentoFrame = null;
					try {
						insereTreinamentoFrame = new InsereTreinamentoView();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					insereTreinamentoFrame.setLocationRelativeTo(null);
					dispatchEvent(new WindowEvent(insereTreinamentoFrame, WindowEvent.WINDOW_OPENED));
				}
			}
		});
		panel.add(btnInserirTreinamento);

		JButton btnGerarRelatorio = new JButton("Gerar");
		btnGerarRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGerarRelatorio.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnGerarRelatorio.setBounds(131, 114, 90, 28);
		btnGerarRelatorio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					setVisible(false);
					GeraRelatorioView geraRelatorioFrame = null;
					try {
						geraRelatorioFrame = new GeraRelatorioView();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					geraRelatorioFrame.setLocationRelativeTo(null);
					dispatchEvent(new WindowEvent(geraRelatorioFrame, WindowEvent.WINDOW_OPENED));
				}
			}
		});
		panel.add(btnGerarRelatorio);

		JLabel lblConsultarRotina = new JLabel("Inserir Treinamento:");
		lblConsultarRotina.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblConsultarRotina.setForeground(Color.WHITE);
		lblConsultarRotina.setBounds(323, 87, 136, 16);
		panel.add(lblConsultarRotina);

		JLabel lblCriarRotina = new JLabel("Gerar Relatorio:");
		lblCriarRotina.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCriarRotina.setForeground(Color.WHITE);
		lblCriarRotina.setBounds(120, 87, 112, 16);
		panel.add(lblCriarRotina);

		JLabel lblNewLabel = new JLabel("Sistema de Gera\u00E7\u00E3o de Relat\u00F3rios de Treinamentos");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(101, 32, 371, 16);
		panel.add(lblNewLabel);
	}
}
