package app.services;
import org.hibernate.EmptyInterceptor;

public class HibernateInterceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        String prepedStatement = super.onPrepareStatement(sql);
        
        prepedStatement = prepedStatement.replaceAll(
                System.getProperty("oldCharInterceptor"), 
                System.getProperty("newCharInterceptor"));
        return prepedStatement;
    }

}
