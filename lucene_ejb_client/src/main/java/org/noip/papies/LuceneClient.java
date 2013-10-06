package org.noip.papies;
 
import javax.naming.Context;
import javax.naming.NamingException;
 
public class LuceneClient {
    public static void main(String[] args) {
        LuceneBeanRemote bean = doLookup();
        String[] terms = bean.terms();
        for(int i=0; i<terms.length; i++){
        	System.out.println(terms[i]);
        }
    }
 
    private static LuceneBeanRemote doLookup() {
        Context context = null;
        LuceneBeanRemote bean = null;
        try {
            // 1. Obtaining Context
            context = LuceneClientUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = getLookupName();
            // 3. Lookup and cast
            bean = (LuceneBeanRemote) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    private static String getLookupName() {
        /*
         * The app name is the EAR name of the deployed EJB without .ear suffix.
         * Since we haven't deployed the application as a .ear, the app name for
         * us will be an empty string
         */
        String appName = "";
 
        /*
         * The module name is the JAR name of the deployed EJB without the .jar
         * suffix.
         */
        String moduleName = "lucene_ejb-1.0-SNAPSHOT";
 
        /*
         * AS7 allows each deployment to have an (optional) distinct name. This
         * can be an empty string if distinct name is not specified.
         */
        String distinctName = "";
 
        // The EJB bean implementation class name
        String beanName = "LuceneBean";
 
        // Fully qualified remote interface name
        final String interfaceName = "org.noip.papies.LuceneBeanRemote";
 
        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
                + "/" + beanName + "!" + interfaceName;
 
        return name;
    }
 
}