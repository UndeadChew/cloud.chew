package cloud.chew.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {

	public static void createTables() {
		try {
			PreparedStatement ps = MySql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Players (DisplayName VARCHAR(32), UUID VARCHAR(32), Currency INT(100), Permissions INT(2), SavedWorld VARCHAR(32), SavedX DOUBLE, SavedY DOUBLE, SavedZ DOUBLE, SavedYaw FLOAT, SavedPitch FLOAT, Organization VARCHAR(32), CurrentIP VARCHAR(32), FirstIP VARCHAR(32), Rank INT(2), JoinDate LONG, PlayTime LONG, IsOnline TINYINT(1), IsHidden TINYINT(1), IsBusy TINYINT(1), PRIMARY KEY (UUID))");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void updatePlayers() {
		try {
			PreparedStatement ps = MySql.getConnection().prepareStatement("INSERT INTO Players(DisplayName , UUID , Currency , Permissions , SavedWorld , SavedX , SavedY , SavedZ , SavedYaw , SavedPitch , Organization , CurrentIP , FirstIP , Rank , JoinDate , PlayTime , IsOnline , IsHidden , IsBusy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, "UndeadChew");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
