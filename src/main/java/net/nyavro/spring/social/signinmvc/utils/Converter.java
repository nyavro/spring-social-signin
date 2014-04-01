package net.nyavro.spring.social.signinmvc.utils;


import net.nyavro.spring.social.signinmvc.model.dto.Contact;
import net.nyavro.spring.social.signinmvc.model.User;

public class Converter {
    public Contact convert(User user) {
        final Contact contact = new Contact();
        contact.setLogin(user.getLogin());
//        contact.setPhone(user.getPhone());
//        contact.setLocation(user.getLocation());
        contact.setEmail(user.getEmail());
        contact.setCity(user.getCity());
        contact.setPrivate(user.isPrivate());
        contact.setLast(user.getLast());
        contact.setFirst(user.getFirst());
        contact.setCompany(user.getCompany());
        contact.setTitle(user.getTitle());
        contact.setId(user.getId());
        contact.setPhone(user.getPhone());
        contact.setEmail(user.getEmail());
        contact.setRegistered(user.getRegistered());
        contact.setLastSeen(user.getLastSeen());
        return contact;
    }
}
