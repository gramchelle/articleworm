--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8
-- Dumped by pg_dump version 16.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: articles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articles (
    article_id integer NOT NULL,
    title character varying(255) NOT NULL,
    article_content character varying(255) NOT NULL,
    user_id integer NOT NULL,
    category_id integer,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.articles OWNER TO postgres;

--
-- Name: articles_article_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.articles_article_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.articles_article_id_seq OWNER TO postgres;

--
-- Name: articles_article_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.articles_article_id_seq OWNED BY public.articles.article_id;


--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    category_id integer NOT NULL,
    category_name character varying(255) NOT NULL,
    description character varying(255)
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: comment_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment_table (
    comment_id integer NOT NULL,
    article_id integer NOT NULL,
    user_id integer NOT NULL,
    comment_content character varying(255) NOT NULL,
    created_at character varying(255) NOT NULL
);


ALTER TABLE public.comment_table OWNER TO postgres;

--
-- Name: comment_table_comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_table_comment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comment_table_comment_id_seq OWNER TO postgres;

--
-- Name: comment_table_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_table_comment_id_seq OWNED BY public.comment_table.comment_id;


--
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comments (
    comment_id integer NOT NULL,
    comment_content character varying(255),
    created_at character varying(255),
    article_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.comments OWNER TO postgres;

--
-- Name: followers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.followers (
    follower_id integer NOT NULL,
    user_id integer NOT NULL,
    follower_user_id integer NOT NULL,
    followed_user_id integer
);


ALTER TABLE public.followers OWNER TO postgres;

--
-- Name: followers_follower_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.followers_follower_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.followers_follower_id_seq OWNER TO postgres;

--
-- Name: followers_follower_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.followers_follower_id_seq OWNED BY public.followers.follower_id;


--
-- Name: notification_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification_user (
    notification_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.notification_user OWNER TO postgres;

--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    notification_id integer NOT NULL,
    user_id integer NOT NULL,
    notification_message character varying(255) NOT NULL,
    is_read boolean DEFAULT false,
    created_at timestamp without time zone NOT NULL,
    article_id integer,
    notification_type character varying(255)
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: notifications_notification_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.notifications_notification_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.notifications_notification_id_seq OWNER TO postgres;

--
-- Name: notifications_notification_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notifications_notification_id_seq OWNED BY public.notifications.notification_id;


--
-- Name: reactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reactions (
    reaction_id integer NOT NULL,
    article_id integer NOT NULL,
    user_id integer NOT NULL,
    reaction_type smallint NOT NULL
);


ALTER TABLE public.reactions OWNER TO postgres;

--
-- Name: reactions_reaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reactions_reaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reactions_reaction_id_seq OWNER TO postgres;

--
-- Name: reactions_reaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reactions_reaction_id_seq OWNED BY public.reactions.reaction_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    passwordhash character varying(255) NOT NULL,
    role_name character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: articles article_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles ALTER COLUMN article_id SET DEFAULT nextval('public.articles_article_id_seq'::regclass);


--
-- Name: comment_table comment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table ALTER COLUMN comment_id SET DEFAULT nextval('public.comment_table_comment_id_seq'::regclass);


--
-- Name: followers follower_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers ALTER COLUMN follower_id SET DEFAULT nextval('public.followers_follower_id_seq'::regclass);


--
-- Name: notifications notification_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications ALTER COLUMN notification_id SET DEFAULT nextval('public.notifications_notification_id_seq'::regclass);


--
-- Name: reactions reaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions ALTER COLUMN reaction_id SET DEFAULT nextval('public.reactions_reaction_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.articles VALUES (3, 'Bali Tourist Guidance', 'Bali’de görülmesi gereken yerler ve ipuçları...', 3, 3, '2025-05-29 11:45:00', '2025-05-29 11:45:00');
INSERT INTO public.articles VALUES (4, 'Online Training Platforms', 'En popüler eğitim platformları karşılaştırması...', 4, 4, '2025-05-29 12:50:00', '2025-05-29 12:50:00');
INSERT INTO public.articles VALUES (5, 'Film Suggestions for 2025', 'Bu yılın en çok beklenen filmleri...', 5, 5, '2025-05-29 14:00:00', '2025-05-29 14:00:00');
INSERT INTO public.articles VALUES (7, 'New Title', 'ornek content', 1, 2, '2025-05-29 18:53:18.605766', '2025-05-29 18:53:18.605766');
INSERT INTO public.articles VALUES (2, 'Updated Title', 'Günlük diyet planı nasıl oluşturulur...', 2, 2, '2025-05-29 10:30:00', '2025-06-01 20:31:32.749745');
INSERT INTO public.articles VALUES (1, 'RESTful Architecture with Spring Boot', 'Spring Boot kullanarak REST servis nasıl hazırlanır...', 1, 1, '2025-05-29 09:00:00', '2025-06-01 20:37:07.79698');


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categories VALUES (1, 'Technology', 'Yazılım, donanım ve teknoloji haberleri');
INSERT INTO public.categories VALUES (2, 'Health', 'Sağlık, beslenme ve yaşam tarzı');
INSERT INTO public.categories VALUES (3, 'Travel', 'Seyahat rehberleri ve gezi notları');
INSERT INTO public.categories VALUES (4, 'Education', 'Eğitim kaynakları ve ipuçları');
INSERT INTO public.categories VALUES (5, 'Entertainment', 'Sinema, müzik ve oyun dünyası');


--
-- Data for Name: comment_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.comment_table VALUES (1, 1, 2, 'It''s a very informative guide, thank you!', '2025-04-15 08:00:00');
INSERT INTO public.comment_table VALUES (2, 1, 3, 'Sample codes are missing, can you help with it?', '2025-04-15 08:30:00');
INSERT INTO public.comment_table VALUES (4, 3, 4, 'Nice content but it is lacking price information.', '2025-04-17 10:20:00');
INSERT INTO public.comment_table VALUES (5, 5, 2, 'I agree with the list so much.', '2025-04-18 11:25:00');
INSERT INTO public.comment_table VALUES (6, 2, 1, 'LIKED IT SO MUCH!!', '2025-06-02 00:38:50');
INSERT INTO public.comment_table VALUES (7, 2, 1, 'This article is a bit unclear', '2025-06-02 00:39:39');
INSERT INTO public.comment_table VALUES (8, 2, 1, 'I did not like it very much.', '2025-06-02 00:43:25');
INSERT INTO public.comment_table VALUES (9, 2, 1, 'IT''S SO INFORMATIVE!!', '2025-06-02 00:44:51');
INSERT INTO public.comment_table VALUES (10, 2, 1, 'This is so insightful!', '2025-06-02 00:51:31');
INSERT INTO public.comment_table VALUES (11, 2, 8, 'I like this!', '2025-06-02 00:51:48');


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: followers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.followers VALUES (1, 1, 2, NULL);
INSERT INTO public.followers VALUES (2, 1, 3, NULL);
INSERT INTO public.followers VALUES (3, 2, 1, NULL);
INSERT INTO public.followers VALUES (4, 3, 4, NULL);
INSERT INTO public.followers VALUES (5, 5, 1, NULL);

--
-- Data for Name: notification_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.notifications VALUES (1, 1, 'Yeni bir makale yayınlandı: Spring Boot ile REST', false, '2025-04-10 09:05:00', NULL, NULL);
INSERT INTO public.notifications VALUES (2, 2, 'Yorum yapıldı: Çok faydalı bir rehber...', false, '2025-04-15 08:05:00', NULL, NULL);
INSERT INTO public.notifications VALUES (3, 3, 'Sizi takip etmeye başladı: alice', true, '2025-04-15 08:10:00', NULL, NULL);
INSERT INTO public.notifications VALUES (4, 4, 'Makaleye tepki geldi: love', false, '2025-04-11 10:35:00', NULL, NULL);
INSERT INTO public.notifications VALUES (5, 5, 'Kategorinize yeni makale eklendi: 2025 Film Önerileri', false, '2025-04-14 14:05:00', NULL, NULL);


--
-- Data for Name: reactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.reactions VALUES (1, 1, 2, 1);
INSERT INTO public.reactions VALUES (2, 1, 3, 3);
INSERT INTO public.reactions VALUES (3, 2, 1, 5);
INSERT INTO public.reactions VALUES (4, 3, 5, 4);
INSERT INTO public.reactions VALUES (5, 5, 4, 6);
INSERT INTO public.reactions VALUES (6, 2, 8, 1);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (1, 'alice', 'alice@example.com', '$2a$10$abcdefg1234567890hijklmnopqrstuv', 'AUTHOR', '2025-04-01 10:15:00');
INSERT INTO public.users VALUES (2, 'bob', 'bob@example.com', '$2a$10$uvwxyz1234567890abcdefgmnopqrst', 'EDITOR', '2025-04-02 11:20:00');
INSERT INTO public.users VALUES (3, 'carol', 'carol@example.com', '$2a$10$1234567890abcdefghijklmnopqrstuv', 'READER', '2025-04-03 12:25:00');
INSERT INTO public.users VALUES (4, 'dave', 'dave@example.com', '$2a$10$qwerty1234567890asdfghjklzxcvbnm', 'AUTHOR', '2025-04-04 13:30:00');
INSERT INTO public.users VALUES (5, 'eve', 'eve@example.com', '$2a$10$mnbvcxzlkjhgfdsapoiuytrewq123456', 'ADMIN', '2025-04-05 14:35:00');
INSERT INTO public.users VALUES (6, 'ozlemnur', 'ozlemnduman34@hotmail.com', 'OIKDo0kd*00dod', 'AUTHOR', '2025-05-31 23:00:28.5688');
INSERT INTO public.users VALUES (7, 'erdem', 'erdemkoyuncu@outlook.com', 'OIKDo0dsfsfkd*00dod', 'AUTHOR', '2025-06-01 20:35:42.513336');
INSERT INTO public.users VALUES (8, 'irem', 'irem@gmail.com', '*00dod', 'EDITOR', '2025-06-01 20:36:04.417244');
INSERT INTO public.users VALUES (9, 'adem', 'ademtatli@gmail.com', '3**sx0osdSA.w', 'READER', '2025-06-01 20:45:42.094343');
INSERT INTO public.users VALUES (10, 'yusuf', 'samplemail@gmail.com', 'x0wo0d*3*2_edwf', 'AUTHOR', '2025-06-01 22:40:29.559788');
INSERT INTO public.users VALUES (11, 'omer', 'samplemail2@gmail.com', 'EDITOR', '12345678$$90abcdefgh$$3902sdlLlmnopqrstuv', '2025-06-01 22:40:53.845841');


--
-- Name: articles_article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.articles_article_id_seq', 9, true);


--
-- Name: comment_table_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_table_comment_id_seq', 11, true);


--
-- Name: followers_follower_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.followers_follower_id_seq', 3, true);


--
-- Name: notifications_notification_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notifications_notification_id_seq', 1, false);


--
-- Name: reactions_reaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reactions_reaction_id_seq', 6, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 16, true);


--
-- Name: articles articles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (article_id);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- Name: comment_table comment_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_pkey PRIMARY KEY (comment_id);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (comment_id);


--
-- Name: followers followers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_pkey PRIMARY KEY (follower_user_id, user_id);


--
-- Name: notification_user notification_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT notification_user_pkey PRIMARY KEY (notification_id, user_id);


--
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (notification_id);


--
-- Name: reactions reactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_pkey PRIMARY KEY (reaction_id);


--
-- Name: users users_passwordhash_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_passwordhash_key UNIQUE (passwordhash);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: articles articles_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(category_id);


--
-- Name: articles articles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: comment_table comment_table_article_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_article_id_fkey FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- Name: comment_table comment_table_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: notification_user fk4hgwdka35gwd4r4023bbcdvrp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT fk4hgwdka35gwd4r4023bbcdvrp FOREIGN KEY (notification_id) REFERENCES public.notifications(notification_id);


--
-- Name: comments fk8omq0tc18jd43bu5tjh6jvraq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk8omq0tc18jd43bu5tjh6jvraq FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: comments fkk4ib6syde10dalk7r7xdl0m5p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkk4ib6syde10dalk7r7xdl0m5p FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- Name: notification_user fkn0b60leyk6c3huiq3doc697wx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT fkn0b60leyk6c3huiq3doc697wx FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: followers followers_follower_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_follower_user_id_fkey FOREIGN KEY (follower_user_id) REFERENCES public.users(user_id);


--
-- Name: followers followers_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: notifications notifications_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: reactions reactions_article_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_article_id_fkey FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- Name: reactions reactions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- PostgreSQL database dump complete
--

