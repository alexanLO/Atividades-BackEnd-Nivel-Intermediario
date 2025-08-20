-- ========================================
-- RELATÓRIO DE LOGIN DOS USUÁRIOS
-- ========================================

-- 1️. USUÁRIOS COM MAIS DE 2 TENTATIVAS DE LOGIN FALHAS NOS ÚLTIMOS 7 DIAS
SELECT user_id
FROM login_attempts
WHERE attempt_time >= CURRENT_TIMESTAMP - 7
  AND success = 0
GROUP BY user_id
HAVING COUNT(*) > 2;


-- 2️. USUÁRIOS COM SEUS PERFIS
SELECT *
FROM users
INNER JOIN profiles ON users.id = profiles.user_id;


-- 3️. NÚMERO DE TENTATIVAS DE LOGIN POR USUÁRIO
SELECT u.username, u.email, COUNT(la.id) AS tentativas
FROM users u
LEFT JOIN login_attempts la ON u.id = la.user_id
GROUP BY u.username, u.email;


-- 4️. USUÁRIOS E A DATA DA ÚLTIMA TENTATIVA DE LOGIN
SELECT u.id, u.username, MAX(la.attempt_time) AS ultima_tentativa
FROM users u
LEFT JOIN login_attempts la ON u.id = la.user_id
GROUP BY u.id, u.username;


-- 5. STATUS DE LOGIN BASEADO NA ÚLTIMA TENTATIVA
SELECT u.id, u.username, la.success, la.attempt_time
FROM users u
JOIN login_attempts la ON u.id = la.user_id
WHERE la.attempt_time = (
   SELECT MAX(sub.attempt_time)
   FROM login_attempts sub
   WHERE sub.user_id = u.id
);

-- ========================================
-- TRANSAÇÔES DE USUÁRIOS
-- ========================================

BEGIN;

-- INSERINDO NOVO USUÁRIO E SEU PERFIL
INSERT INTO users(username, email, passwd, status, role)
VALUES ('John Doe', 'jhonDoe@Empresa.com', 'senha12@', 1, 'admin');

INSERT INTO profiles(user_id, bio, avatar_url) VALUES
(1, 'Desenvolvedor de Software', 'http://example.com/avatar.jpg');

-- SIMULANDO TENTATIVA DE LOGIN BEM-SUCEDIDA
INSERT INTO login_attempts(user_id, attempt_time, success) VALUES
(1, CURRENT_TIMESTAMP, 1);

COMMIT;