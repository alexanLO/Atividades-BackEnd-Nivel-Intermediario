BEGIN
   EXECUTE IMMEDIATE '
      CREATE TABLE permissions (
         id   NUMBER PRIMARY KEY,
         name VARCHAR2(150) NOT NULL UNIQUE
      )
   ';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
         RAISE;
      END IF;
END;
/

BEGIN
   EXECUTE IMMEDIATE '
      CREATE TABLE user_permissions (
         user_id       RAW(16) NOT NULL,
         permission_id NUMBER NOT NULL,
         PRIMARY KEY (user_id, permission_id),
         CONSTRAINT fk_user FOREIGN KEY (user_id)
            REFERENCES users(id),
         CONSTRAINT fk_permission FOREIGN KEY (permission_id)
            REFERENCES permissions(id)
      )
   ';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -955 THEN
         RAISE;
      END IF;
END;