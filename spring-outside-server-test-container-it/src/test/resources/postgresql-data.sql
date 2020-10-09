BEGIN TRANSACTION;

INSERT INTO
    ARTIST(AR_ID, AR_NAME, AR_GENRE)
VALUES
    (nextval('ARTIST_SEQ'), 'artist_1', 'rock'),
    (nextval('ARTIST_SEQ'), 'artist_2', 'jazz'),
    (nextval('ARTIST_SEQ'), 'artist_3', 'electronic'),
    (nextval('ARTIST_SEQ'), 'artist_4', 'minimal'),
    (nextval('ARTIST_SEQ'), 'artist_5', 'funk');


INSERT INTO
    ALBUM(AL_ID, AL_AR_ID, AL_NAME, AL_SONGS)
VALUES
    (nextval('ALBUM_SEQ'), 1, 'artist_1_album_1', 'song_1, song_2, song_3'),
    (nextval('ALBUM_SEQ'), 1, 'artist_1_album_2', 'song_1, song_2'),
    (nextval('ALBUM_SEQ'), 1, 'artist_1_album_3', 'song_1, song_2, song_3, song_4'),

    (nextval('ALBUM_SEQ'), 2, 'artist_2_album_1', 'song_1, song_2'),
    (nextval('ALBUM_SEQ'), 2, 'artist_2_album_2', 'song_1, song_2, song_3'),

    (nextval('ALBUM_SEQ'), 3, 'artist_3_album_1', 'song_1, song_2'),
    (nextval('ALBUM_SEQ'), 3, 'artist_3_album_2', 'song_1, song_2, song_3, song_4, song_5'),
    (nextval('ALBUM_SEQ'), 3, 'artist_3_album_3', 'song_1, song_2, song_3'),

    (nextval('ALBUM_SEQ'), 4, 'artist_4_album_1', 'song_1, song_2'),
    (nextval('ALBUM_SEQ'), 4, 'artist_4_album_2', 'song_1, song_2, song_3'),

    (nextval('ALBUM_SEQ'), 5, 'artist_5_album_1', 'song_1');

COMMIT;