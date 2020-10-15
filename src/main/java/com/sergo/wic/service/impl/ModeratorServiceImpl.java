//package com.sergo.wic.service.impl;
//
//import com.sergo.wic.entities.Moderator;
//import com.sergo.wic.entities.Role;
//import com.sergo.wic.repository.ModeratorRepository;
//import com.sergo.wic.repository.RoleRepository;
//import com.sergo.wic.service.ModeratorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//@Service
//public class ModeratorServiceImpl implements ModeratorService {
//    private ModeratorRepository repository;
//    private RoleRepository roleRepository;
//
//    public ModeratorServiceImpl(){}
//
//    public ModeratorServiceImpl(@Autowired ModeratorRepository repository,
//                              @Autowired RoleRepository roleRepository){
//        this.repository = repository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public Moderator findByLogin(String login) {
//        return repository.findByLogin(login).orElse(null);
//    }
//
//    @Override
//    public boolean existByLogin(String login) {
//        return repository.existsByLogin(login);
//    }
//
//    @Override
//    public boolean checkLoginAndPassword(String login, String password) {
//        Moderator moderator = findByLogin(login);
//        if (moderator != null ){
//            return (moderator.getPassword().equals(password));
//        }
//        return false;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Moderator moderator = findByLogin(login);
//        if (moderator == null) {
//            throw new UsernameNotFoundException("Invalid username or password");
//        }
//        moderator.getRoles().forEach(role -> System.out.println(role.getName() + " role"));
//        return new org.springframework.security.core.userdetails.User(
//                moderator.getLogin(),
//                moderator.getPassword(),
//                mapRolesToAuthorities(moderator.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//}
