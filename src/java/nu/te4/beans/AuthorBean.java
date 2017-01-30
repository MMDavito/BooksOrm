package nu.te4.beans;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.entities.Author;
import nu.te4.sessionbeans.AuthorFacade;

@Named
@SessionScoped
public class AuthorBean implements Serializable {

    @EJB
    AuthorFacade authorFacade;

    public List<Author> getAuthors() {
        return authorFacade.findAll();
    }

    public ArrayList<String> choices(String key_word) {
        ArrayList<String> choices = new ArrayList<>();
        try {
            Connection conn = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost/books_orm", "root", "");
            String sql = "SELECT name FROM author WHERE name LIKE %?%";
            PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
            stmt.setString(1, key_word);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                choices.add(rs.getString("name"));

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Author " + e);
        }
        return choices;
    }

}
