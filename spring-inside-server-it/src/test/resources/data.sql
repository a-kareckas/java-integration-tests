BEGIN TRANSACTION;

INSERT INTO
    ARTIST(AR_ID, AR_NAME, AR_GENRE)
VALUES
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_1', 'rock'),
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_2', 'jazz'),
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_3', 'electronic'),
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_4', 'minimal'),
    (NEXT VALUE FOR ARTIST_SEQ, 'artist_5', 'funk');


INSERT INTO
    ALBUM(AL_ID, AL_AR_ID, AL_NAME, AL_SONGS)
VALUES
    (NEXT VALUE FOR ALBUM_SEQ, 1, 'artist_1_album_1', 'song_1, song_2, song_3'),
    (NEXT VALUE FOR ALBUM_SEQ, 1, 'artist_1_album_2', 'song_1, song_2'),
    (NEXT VALUE FOR ALBUM_SEQ, 1, 'artist_1_album_3', 'song_1, song_2, song_3, song_4'),

    (NEXT VALUE FOR ALBUM_SEQ, 2, 'artist_2_album_1', 'song_1, song_2'),
    (NEXT VALUE FOR ALBUM_SEQ, 2, 'artist_2_album_2', 'song_1, song_2, song_3'),

    (NEXT VALUE FOR ALBUM_SEQ, 3, 'artist_3_album_1', 'song_1, song_2'),
    (NEXT VALUE FOR ALBUM_SEQ, 3, 'artist_3_album_2', 'song_1, song_2, song_3, song_4, song_5'),
    (NEXT VALUE FOR ALBUM_SEQ, 3, 'artist_3_album_3', 'song_1, song_2, song_3'),

    (NEXT VALUE FOR ALBUM_SEQ, 4, 'artist_4_album_1', 'song_1, song_2'),
    (NEXT VALUE FOR ALBUM_SEQ, 4, 'artist_4_album_2', 'song_1, song_2, song_3'),

    (NEXT VALUE FOR ALBUM_SEQ, 5, 'artist_5_album_1', 'song_1');

COMMIT;