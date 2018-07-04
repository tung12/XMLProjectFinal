
package jaxb.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.user package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Users_QNAME = new QName("http://xml.netbeans.org/schema/users", "users");
    private final static QName _Roles_QNAME = new QName("http://xml.netbeans.org/schema/role", "roles");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListUser }
     * 
     */
    public ListUser createListUser() {
        return new ListUser();
    }

    /**
     * Create an instance of {@link UserItem }
     * 
     */
    public UserItem createUserItem() {
        return new UserItem();
    }

    /**
     * Create an instance of {@link ListRole }
     * 
     */
    public ListRole createListRole() {
        return new ListRole();
    }

    /**
     * Create an instance of {@link RoleItem }
     * 
     */
    public RoleItem createRoleItem() {
        return new RoleItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/users", name = "users")
    public JAXBElement<ListUser> createUsers(ListUser value) {
        return new JAXBElement<ListUser>(_Users_QNAME, ListUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/role", name = "roles")
    public JAXBElement<ListRole> createRoles(ListRole value) {
        return new JAXBElement<ListRole>(_Roles_QNAME, ListRole.class, null, value);
    }

}
