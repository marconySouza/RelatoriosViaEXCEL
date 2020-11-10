/**
 * @author marcony.souza
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dao.FuncionarioDAO;
import vo.Funcionario;

/**
 * @author marcony.souza
 *
 */
public class GeraRelatorioView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public GeraRelatorioView() throws SQLException {
		final FuncionarioDAO dao = new FuncionarioDAO();
		
		setAutoRequestFocus(false);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		setOpacity(1.0f);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GeraRelatorioView.class.getResource("/resources/relatorio.png")));
		setTitle("Relatorio de Treinamentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/** Painel **/

		final JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		/** Labels **/
		final JLabel lblDtFinal = new JLabel("Repetir a cada:");

		JLabel lblNomeDoFuncionario = new JLabel("Funcionario:");
		lblNomeDoFuncionario.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNomeDoFuncionario.setForeground(Color.WHITE);
		lblNomeDoFuncionario.setBounds(252, 72, 69, 16);
		panel.add(lblNomeDoFuncionario);
		
		
		
		final JComboBox<String> comboFuncionario = new JComboBox<String>();
		comboFuncionario.setModel(new DefaultComboBoxModel<String>(dao.funcionarios()));
		comboFuncionario.setBounds(109, 96, 356, 24);
		panel.add(comboFuncionario);

		JLabel lblPeriodicidade = new JLabel("Periodicidade:");
		lblPeriodicidade.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPeriodicidade.setForeground(Color.WHITE);
		lblPeriodicidade.setBounds(244, 143, 86, 16);
		panel.add(lblPeriodicidade);

		lblDtFinal.setText("e");
		lblDtFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDtFinal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDtFinal.setForeground(Color.WHITE);
		lblDtFinal.setBounds(288, 181, 27, 22);
		lblDtFinal.setVisible(true);
		panel.add(lblDtFinal);

		/** Buttons **/

		/** TextFields, ComboBox, CheckBox **/
		final JComboBox<String> dtInicial = new JComboBox<String>();

		final JComboBox<String> dtFinal = new JComboBox<String>();
		dtFinal.setModel(new DefaultComboBoxModel<String>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022",
				"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035",
				"2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048",
				"2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060" }));
		dtFinal.setBounds(325, 180, 56, 24);
		panel.add(dtFinal);

		dtInicial.setModel(new DefaultComboBoxModel<String>(
				new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025",
						"2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037",
						"2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049",
						"2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060" }));
		dtInicial.setBounds(222, 181, 56, 22);
		dtInicial.setVisible(true);
		panel.add(dtInicial);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnVoltar.setBackground(Color.YELLOW);
		btnVoltar.setBounds(484, 318, 80, 22);

		btnVoltar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					setVisible(false);
					DashboardView dashboardFrame = new DashboardView();
					dashboardFrame.setLocationRelativeTo(null);
					dispatchEvent(new WindowEvent(dashboardFrame, WindowEvent.WINDOW_OPENED));

				}
			}
		});

		panel.add(btnVoltar);

		JLabel lblDtInicio = new JLabel("Entre");
		lblDtInicio.setForeground(Color.WHITE);
		lblDtInicio.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDtInicio.setBounds(174, 184, 43, 16);
		panel.add(lblDtInicio);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnGerar.setBounds(244, 244, 86, 36);
		btnGerar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					Funcionario func = new Funcionario();
					func.setNomeFuncionario((String) comboFuncionario.getSelectedItem());

					try {
						if (dao.gerarRelatorio(func, dtInicial.getSelectedItem().toString(),
								dtFinal.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso na pasta do programa", "SUCESSO!!", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Erro ao gerar relatório, falar com Marcony", "ERRO!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Relatório em uso, feche-o para prosseguir", "ERRO!", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro ao inserir treinamento no banco de dados, consultar Marcony", "ERRO!", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});

		panel.add(btnGerar);
	}
}
