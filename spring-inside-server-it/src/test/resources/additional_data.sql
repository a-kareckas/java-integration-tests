BEGIN TRANSACTION;

INSERT INTO
    ARTIST(AR_ID, AR_NAME, AR_GENRE)
VALUES
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_6', 'dubstep');


INSERT INTO
    ALBUM(AL_ID, AL_AR_ID, AL_NAME, AL_SONGS)
VALUES
    (NEXT VALUE FOR ALBUM_SEQ, 1, 'artist_6_album_1', 'song_1, song_2, song_3'),
    (NEXT VALUE FOR ALBUM_SEQ, 1, 'artist_6_album_2', 'song_1, song_2');

COMMIT;