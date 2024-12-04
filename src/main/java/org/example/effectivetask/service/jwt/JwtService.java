package org.example.effectivetask.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Сервис для работы с JWT (JSON Web Token).
 * Предоставляет методы для извлечения информации из токена, проверки его валидности и генерации нового токена.
 */
public interface JwtService {

    /**
     * Извлекает субъект (обычно это username или email) из JWT токена.
     *
     * @param token JWT токен.
     * @return Субъект (обычно это username или email) из токена.
     */
    String extractSubject(String token);

    /**
     * Проверяет, является ли JWT токен валидным для указанного пользователя.
     *
     * @param token JWT токен.
     * @param userDetails Детали пользователя, с которыми нужно сверить токен.
     * @return true, если токен валиден для данного пользователя, иначе false.
     */
    Boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Генерирует новый JWT токен для указанного пользователя.
     *
     * @param userDetails Детали пользователя, для которого будет сгенерирован токен.
     * @return Сгенерированный JWT токен.
     */
    String generateToken(UserDetails userDetails);
}


