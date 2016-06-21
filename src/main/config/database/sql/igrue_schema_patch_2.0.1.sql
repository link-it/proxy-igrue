CREATE SEQUENCE sq_trasmissioni START ${igrue.db.sequencestart};
ALTER TABLE utenti ADD COLUMN abilitato BOOLEAN DEFAULT true ;
