package br.com.gorilahack.projectnavi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.gorilahack.projectnavi.ConnectionFactory;
import br.com.gorilahack.projectnavi.tos.EnderecoEstabelecimentoTO;

@Repository
public class CeInfoRepository {
	
	public CeInfoRepository() {	
	}
	
	public List<EnderecoEstabelecimentoTO> getEstabelecimentos(String codigoEspecialidade) {
		List<EnderecoEstabelecimentoTO> listEndEstTO = new ArrayList<EnderecoEstabelecimentoTO>();
		
		String sqlStr = " SELECT latitudegoogle, longitudegoogle, endereco, numero, bairro, cidade, estado, SQRT( " +
			    		" POW(69.1 * (latitudegoogle - 23.5364494), 2) + " +
			    		" POW(69.1 * (46.6462343 - longitudegoogle) * COS(latitudegoogle / 57.3), 2)) AS distance " +
			    		" FROM estabelecimento " + 
			    		" WHERE latitudegoogle <> ? " +
			    		"   AND longitudegoogle <> ? " +
			    		"   AND tipo_servico like ? " +
			    		" ORDER BY distance " +
			    		" LIMIT 1 ";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
			 PreparedStatement stm = conn.prepareStatement(sqlStr);) {

			stm.setString(1, "0");
			stm.setString(2, "0");
			stm.setString(3, "%" + codigoEspecialidade + "%");
			
			
			try (ResultSet rs = stm.executeQuery();) {

				if (rs.next()) {
					String endereco = rs.getString("endereco") + ", " +
							rs.getString("numero") + ", " +
							rs.getString("bairro") + ", " +
							rs.getString("cidade") + ", " +
							rs.getString("estado") + ", " +
							rs.getString("distance");
					
					EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();
					to.setEndereco(endereco);
					to.setLatitude(rs.getString("latitudegoogle"));
					to.setLongitude(rs.getString("longitudegoogle"));
					listEndEstTO.add(to);
										
					System.out.println(endereco);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}				
			
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return listEndEstTO; 
	}

	
	public List<EnderecoEstabelecimentoTO> getEstabelecimentosComEstoque(String codigoProduto) {
		List<EnderecoEstabelecimentoTO> listEndEstTO = new ArrayList<EnderecoEstabelecimentoTO>();
		
		String sqlStr = " SELECT latitudegoogle, longitudegoogle, endereco, numero, bairro, cidade, estado, SQRT( " +
			    		" POW(69.1 * (latitudegoogle - 23.5364494), 2) + " +
			    		" POW(69.1 * (46.6462343 - longitudegoogle) * COS(latitudegoogle / 57.3), 2)) AS distance " +
			    		" FROM estabelecimento " + 
			    		" INNER JOIN estoqueprodutos on (estoqueprodutos.cnes_estabelecimento = estabelecimento.cness) and (estoqueprodutos.estoque > 0) and (estoqueprodutos.codigo_supri_item = ?)" +
			    		" WHERE latitudegoogle <> ? " +
			    		"   AND longitudegoogle <> ? " +
			    		" ORDER BY distance " +
			    		" LIMIT 1 ";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
			 PreparedStatement stm = conn.prepareStatement(sqlStr);) {

			stm.setString(1, codigoProduto);
			stm.setString(2, "0");
			stm.setString(3, "0");
			
			
			try (ResultSet rs = stm.executeQuery();) {

				if (rs.next()) {
					String endereco = rs.getString("endereco") + ", " +
							rs.getString("numero") + ", " +
							rs.getString("bairro") + ", " +
							rs.getString("cidade") + ", " +
							rs.getString("estado") + ", " +
							rs.getString("distance");
					
					EnderecoEstabelecimentoTO to = new EnderecoEstabelecimentoTO();
					to.setEndereco(endereco);
					to.setLatitude(rs.getString("latitudegoogle"));
					to.setLongitude(rs.getString("longitudegoogle"));
					listEndEstTO.add(to);
										
					System.out.println(endereco);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}				
			
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return listEndEstTO; 
	}
	
}
