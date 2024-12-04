package org.example.effectivetask.service.user;

import org.example.effectivetask.model.entity.User;

/**
 * Сервис для работы с пользователями.
 */
public interface UserService {

    /**
     * Получает пользователя по имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Пользователь с заданным именем.
     */
    User getUser(String username);

    /**
     * Получает пользователя по ID.
     *
     * @param id ID пользователя.
     * @return Пользователь с заданным ID.
     */
    User getUser(Long id);
}
