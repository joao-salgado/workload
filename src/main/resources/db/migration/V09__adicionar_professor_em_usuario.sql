ALTER TABLE usuario ADD codigo_professor BIGINT(20);

ALTER TABLE usuario ADD CONSTRAINT fk_TB_Usuario_TB_Professor
FOREIGN KEY(codigo_professor) REFERENCES professor(codigo);