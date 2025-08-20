
-- USUÁRIOS:
INSERT INTO users(username, email, passwd, status, creationDate, role)  
    VALUES ('Ted Ferry', 'ted.ferry@Empresa.com', 'SeAIn4wXl@', 1, CURRENT_TIMESTAMP, 'ADMIN');
INSERT INTO users(username, email, passwd, status, creationDate, role)  
    VALUES('Raquel Tremblay', 'raquel.tremblay@Empresa.com', '0z5akdMYD1IjfkB@', 0, CURRENT_TIMESTAMP, 'ADMIN');
INSERT INTO users(username, email, passwd, status, creationDate, role)  
    VALUES('Mandy Stanton', 'mandy.stanton@Empresa.com', 'nDV5tOZLPiDXnRp@', 1, CURRENT_TIMESTAMP, 'ADMIN');
INSERT INTO users(username, email, passwd, status, creationDate, role)  
    VALUES('Jasmine Hansen', 'jasmine.hansei@Empresa.com', 'U0lvnazN3zzqKOQ@', 0, CURRENT_TIMESTAMP, 'USER');

-- PERFIS DOS USUÁRIOS:
INSERT INTO profiles(user_id, bio, avatar_url) 
    VALUES ((SELECT id FROM users WHERE email = 'ted.ferry@Empresa.com'), 'Gerente', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/484.jpg');
INSERT INTO profiles(user_id, bio, avatar_url) 
    VALUES ((SELECT id FROM users WHERE email = 'raquel.tremblay@Empresa.com'), 'Secretaria', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/484.jpg');
INSERT INTO profiles(user_id, bio, avatar_url) 
    VALUES ((SELECT id FROM users WHERE email = 'mandy.stanton@Empresa.com'), 'Desenvolvedora de Software', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/484.jpg');

-- TENTATIVAS DE LOGIN:
INSERT INTO login_attempts(user_id, attempt_time, success) 
    VALUES ((SELECT id FROM users WHERE email = 'ted.ferry@Empresa.com'), CURRENT_TIMESTAMP, 0);
INSERT INTO login_attempts(user_id, attempt_time, success) 
    VALUES ((SELECT id FROM users WHERE email = 'ted.ferry@Empresa.com'), CURRENT_TIMESTAMP, 1);
INSERT INTO login_attempts(user_id, attempt_time, success) 
    VALUES ((SELECT id FROM users WHERE email = 'mandy.stanton@Empresa.com'), CURRENT_TIMESTAMP, 1);
INSERT INTO login_attempts(user_id, attempt_time, success) 
    VALUES ((SELECT id FROM users WHERE email = 'jasmine.hansei@Empresa.com'), CURRENT_TIMESTAMP, 0);
INSERT INTO login_attempts(user_id, attempt_time, success) 
    VALUES ((SELECT id FROM users WHERE email = 'jasmine.hansei@Empresa.com'), CURRENT_TIMESTAMP, 0);