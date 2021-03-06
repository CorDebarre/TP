package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.config.BddConnection;
import com.dto.VilleFrance;

public class VilleFranceDAO {
	
	
	public VilleFranceDAO() {
		super();
	}

	public boolean creationVilleFrance(VilleFrance ville) {

		boolean init = false;
		try {
			Connection conn = BddConnection.getInstance();

			String query = "INSERT INTO ville_france";
			query += "(Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude)";
			query += " VALUES(?,?,?,?,?,?,?)";

			PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			prepare.setString(1, ville.getCode_commune_INSEE());
			prepare.setString(2, ville.getNom_commune());
			prepare.setString(3, ville.getCode_postal());
			prepare.setString(4, ville.getLibelle_acheminement());
			prepare.setString(5, ville.getLigne_5());
			prepare.setString(6, ville.getLatitude());
			prepare.setString(7, ville.getLongitude());

			prepare.executeUpdate();
			init = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return init;
	}
	
	
	public ArrayList<VilleFrance> listeVillesFrance() {
		ArrayList <VilleFrance> villes = null;

		try {
			Connection conn = BddConnection.getInstance();
			String query = "SELECT * FROM ville_france";

			PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet res = prepare.executeQuery();
			res.last();
			res.beforeFirst();

			villes = new ArrayList<VilleFrance>();

		
			while (res.next()) {
				VilleFrance vf = new VilleFrance();

				vf.setCode_commune_INSEE(res.getString("Code_commune_INSEE"));
				vf.setCode_postal(res.getString("Code_postal"));
				vf.setNom_commune(res.getString("Nom_commune"));
				vf.setLibelle_acheminement(res.getString("Libelle_acheminement"));
				vf.setLigne_5(res.getString("Ligne_5"));
				vf.setLatitude(res.getString("Latitude"));
				vf.setLongitude(res.getString("Longitude"));
				

				villes.add(vf);
				
			}

			res.close();
			prepare.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return villes;
	}
	
	public ArrayList<VilleFrance> trouver(String filtre,String valeur) {
		ArrayList <VilleFrance> villes = null;

		try {
			Connection conn = BddConnection.getInstance();
			String query = "SELECT * FROM ville_france WHERE " + filtre +"=" + valeur;
			System.out.println(query);

			PreparedStatement prepare = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet res = prepare.executeQuery();
			res.last();
			res.beforeFirst();

			villes = new ArrayList<VilleFrance>();

		
			while (res.next()) {
				VilleFrance vf = new VilleFrance();

				vf.setCode_commune_INSEE(res.getString("Code_commune_INSEE"));
				vf.setCode_postal(res.getString("Code_postal"));
				vf.setNom_commune(res.getString("Nom_commune"));
				vf.setLibelle_acheminement(res.getString("Libelle_acheminement"));
				vf.setLigne_5(res.getString("Ligne_5"));
				vf.setLatitude(res.getString("Latitude"));
				vf.setLongitude(res.getString("Longitude"));
				System.out.println(vf.getNom_commune());

				villes.add(vf);
				
			}

			res.close();
			prepare.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return villes;
	}
	
	public VilleFrance modifier(VilleFrance ville, String code_commune) {
		

		try {
			Connection conn = BddConnection.getInstance();
			String update = "UPDATE ville_france SET Code_commune_INSEE='"+ville.getCode_commune_INSEE()+ "', Code_postal='"+ville.getCode_postal()+ "', Nom_commune='"+ville.getNom_commune()+"', Libelle_acheminement='"+ville.getLibelle_acheminement()+"', Ligne_5='"+ville.getLigne_5()+"', Latitude='"+ville.getLatitude()+"', Longitude='"+ville.getLongitude()+"' WHERE Code_commune_INSEE='"+code_commune+"'" ;
			System.out.println(update);

			PreparedStatement prepare = conn.prepareStatement(update, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int res = 0;
			res=prepare.executeUpdate();
			
			prepare.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return ville;
	}
	
public String supprimer(String code_commune) {
		

		try {
			Connection conn = BddConnection.getInstance();
			String update = "DELETE FROM ville_france WHERE Code_commune_INSEE='"+code_commune +"'";
			System.out.println(update);

			PreparedStatement prepare = conn.prepareStatement(update, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			int res = 0;
			res=prepare.executeUpdate();
			
			prepare.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "La ville a été supprimée";
	}
	
	
	
}
	


