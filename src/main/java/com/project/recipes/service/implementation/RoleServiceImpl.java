package com.project.recipes.service.implementation;

import com.project.recipes.exception.NullEntityReferenceException;
import com.project.recipes.model.Role;
import com.project.recipes.repository.RoleRepository;
import com.project.recipes.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        if (role != null) {
            logger.info("Role success create");
            return roleRepository.save(role);
        }
        logger.error("Role  cannot 'null'");
        throw new NullEntityReferenceException("Role cannot be 'null'");
    }

    @Override
    public Role readById(long id) {
        logger.info("get ById id=" + id);
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));
    }

    @Override
    public Role update(Role role) {
        if (role != null) {
            readById(role.getId());
            logger.info("Updated role " + role);
            return roleRepository.save(role);
        }
        logger.error("Role to update cannot be 'null'");
        throw new NullEntityReferenceException("Role cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
        logger.info("Delete role by ID=" + id);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        logger.info("Get all Roles");
        return roleRepository.findAll();
    }

    @Override
    public Role readByName(String name) {
        logger.info("readByName name:" + name);
        return roleRepository.findRoleByName(name).orElseThrow(() -> new EntityNotFoundException("Role " + name + " not found"));
    }
}
