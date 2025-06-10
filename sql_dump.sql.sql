--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2
-- Dumped by pg_dump version 17.2

-- Started on 2025-06-09 11:05:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 217 (class 1259 OID 20291)
-- Name: articles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.articles (
    article_id integer NOT NULL,
    title character varying(255) NOT NULL,
    article_content text NOT NULL,
    user_id integer NOT NULL,
    category_id integer,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.articles OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 20296)
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
-- TOC entry 4987 (class 0 OID 0)
-- Dependencies: 218
-- Name: articles_article_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.articles_article_id_seq OWNED BY public.articles.article_id;


--
-- TOC entry 232 (class 1259 OID 20453)
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_category_id_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 20297)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    category_id integer DEFAULT nextval('public.categories_category_id_seq'::regclass) NOT NULL,
    category_name character varying(255) NOT NULL,
    description character varying(255)
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 20302)
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
-- TOC entry 221 (class 1259 OID 20307)
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
-- TOC entry 4988 (class 0 OID 0)
-- Dependencies: 221
-- Name: comment_table_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_table_comment_id_seq OWNED BY public.comment_table.comment_id;


--
-- TOC entry 222 (class 1259 OID 20308)
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
-- TOC entry 223 (class 1259 OID 20313)
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
-- TOC entry 224 (class 1259 OID 20316)
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
-- TOC entry 4989 (class 0 OID 0)
-- Dependencies: 224
-- Name: followers_follower_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.followers_follower_id_seq OWNED BY public.followers.follower_id;


--
-- TOC entry 225 (class 1259 OID 20317)
-- Name: notification_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notification_user (
    notification_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.notification_user OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 20320)
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
-- TOC entry 227 (class 1259 OID 20326)
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
-- TOC entry 4990 (class 0 OID 0)
-- Dependencies: 227
-- Name: notifications_notification_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.notifications_notification_id_seq OWNED BY public.notifications.notification_id;


--
-- TOC entry 228 (class 1259 OID 20327)
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
-- TOC entry 229 (class 1259 OID 20330)
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
-- TOC entry 4991 (class 0 OID 0)
-- Dependencies: 229
-- Name: reactions_reaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reactions_reaction_id_seq OWNED BY public.reactions.reaction_id;


--
-- TOC entry 230 (class 1259 OID 20331)
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
-- TOC entry 231 (class 1259 OID 20336)
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
-- TOC entry 4992 (class 0 OID 0)
-- Dependencies: 231
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 4780 (class 2604 OID 20337)
-- Name: articles article_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles ALTER COLUMN article_id SET DEFAULT nextval('public.articles_article_id_seq'::regclass);


--
-- TOC entry 4782 (class 2604 OID 20338)
-- Name: comment_table comment_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table ALTER COLUMN comment_id SET DEFAULT nextval('public.comment_table_comment_id_seq'::regclass);


--
-- TOC entry 4783 (class 2604 OID 20339)
-- Name: followers follower_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers ALTER COLUMN follower_id SET DEFAULT nextval('public.followers_follower_id_seq'::regclass);


--
-- TOC entry 4784 (class 2604 OID 20340)
-- Name: notifications notification_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications ALTER COLUMN notification_id SET DEFAULT nextval('public.notifications_notification_id_seq'::regclass);


--
-- TOC entry 4786 (class 2604 OID 20341)
-- Name: reactions reaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions ALTER COLUMN reaction_id SET DEFAULT nextval('public.reactions_reaction_id_seq'::regclass);


--
-- TOC entry 4787 (class 2604 OID 20342)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 4966 (class 0 OID 20291)
-- Dependencies: 217
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.articles VALUES (3, 'Bali Tourist Guidance', 'Bali’de görülmesi gereken yerler ve ipuçları...', 3, 3, '2025-05-29 11:45:00', '2025-05-29 11:45:00');
INSERT INTO public.articles VALUES (4, 'Online Training Platforms', 'En popüler eğitim platformları karşılaştırması...', 4, 4, '2025-05-29 12:50:00', '2025-05-29 12:50:00');
INSERT INTO public.articles VALUES (5, 'Film Suggestions for 2025', 'Bu yılın en çok beklenen filmleri...', 5, 5, '2025-05-29 14:00:00', '2025-05-29 14:00:00');
INSERT INTO public.articles VALUES (7, 'New Title', 'ornek content', 1, 2, '2025-05-29 18:53:18.605766', '2025-05-29 18:53:18.605766');
INSERT INTO public.articles VALUES (2, 'Updated Title', 'Günlük diyet planı nasıl oluşturulur...', 2, 2, '2025-05-29 10:30:00', '2025-06-01 20:31:32.749745');
INSERT INTO public.articles VALUES (1, 'RESTful Architecture with Spring Boot', 'Spring Boot kullanarak REST servis nasıl hazırlanır...', 1, 1, '2025-05-29 09:00:00', '2025-06-01 20:37:07.79698');
INSERT INTO public.articles VALUES (10, 'This is my title', '<p><strong>Hello world</strong></p>', 38, 1, '2025-06-06 00:32:50.157386', '2025-06-06 00:32:50.157386');
INSERT INTO public.articles VALUES (11, 'This is my another title', '<p>Again <mark data-color="var(--tt-color-highlight-red)" style="background-color: var(--tt-color-highlight-red); color: inherit">hello world</mark>…</p><p><strong>how <em>creative</em></strong></p>', 38, 1, '2025-06-06 00:38:16.295571', '2025-06-06 00:38:16.295571');
INSERT INTO public.articles VALUES (12, 'This is another title', 'example content test', 38, 1, '2025-06-06 11:12:32.399881', '2025-06-06 11:12:32.399881');
INSERT INTO public.articles VALUES (13, 'This is my title', '<p>qweqwe</p>', 39, 1, '2025-06-06 11:31:46.602729', '2025-06-06 11:31:46.602729');
INSERT INTO public.articles VALUES (14, 'This is dwoqdh aw', '<p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Tell your story...</mark></p>', 39, 1, '2025-06-06 11:32:33.203465', '2025-06-06 11:32:33.203465');
INSERT INTO public.articles VALUES (15, 'This is dwoqdh aw', '<p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Tell your story...</mark></p>', 39, 1, '2025-06-06 11:33:30.51336', '2025-06-06 11:33:30.51336');
INSERT INTO public.articles VALUES (16, 'This is dwoqdh aw', '<p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Tell your story... </mark><strong>qw dhqow hdoqwh doqwhod q</strong><em>w qw dqwd qwd</em></p>', 39, 1, '2025-06-06 11:33:57.192329', '2025-06-06 11:33:57.192329');
INSERT INTO public.articles VALUES (17, 'This is dwoqdh aw', '<p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Tell your story... </mark><strong>qw dhqow hdoqwh doqwhod q</strong><em>w qw dqwd qwd qwdh ouqwho ho<u>d</u></em></p>', 39, 1, '2025-06-06 11:34:39.982191', '2025-06-06 11:34:39.982191');
INSERT INTO public.articles VALUES (18, 'This is dwoqdh aw', '<p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Tell your story... </mark><strong>qw dhqow hdoqwh doqwhod q</strong><em>w qw dqwd qwd qwdh ouqwho ho<u>dwoqi hdow</u></em></p>', 39, 1, '2025-06-06 11:35:16.266405', '2025-06-06 11:35:16.266405');
INSERT INTO public.articles VALUES (19, 'Test1', '<p><strong>Tell your story...</strong> <em>Tell your story... </em><code>Tell your story..</code>.<mark data-color="var(--tt-color-highlight-red)" style="background-color: var(--tt-color-highlight-red); color: inherit">Tell your story...</mark></p>', 39, 1, '2025-06-06 12:15:38.97088', '2025-06-06 12:15:38.97088');
INSERT INTO public.articles VALUES (20, 'Test1', '<p>a</p><p>a</p><p>a</p>', 39, 1, '2025-06-06 12:18:41.051534', '2025-06-06 12:18:41.051534');
INSERT INTO public.articles VALUES (21, 'Test1', '<p>a</p><p><code>a</code></p><p>a</p>', 39, 1, '2025-06-06 12:18:47.067028', '2025-06-06 12:18:47.067028');
INSERT INTO public.articles VALUES (22, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p>', 39, 1, '2025-06-06 12:18:53.852365', '2025-06-06 12:18:53.852365');
INSERT INTO public.articles VALUES (23, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p>a</p>', 39, 1, '2025-06-06 12:19:10.141184', '2025-06-06 12:19:10.141184');
INSERT INTO public.articles VALUES (24, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><strong>a</strong></p>', 39, 1, '2025-06-06 12:19:14.251868', '2025-06-06 12:19:14.251868');
INSERT INTO public.articles VALUES (25, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><strong><em>a</em></strong></p>', 39, 1, '2025-06-06 12:19:17.672803', '2025-06-06 12:19:17.672803');
INSERT INTO public.articles VALUES (26, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><strong><em><s>a</s></em></strong></p>', 39, 1, '2025-06-06 12:19:19.57683', '2025-06-06 12:19:19.57683');
INSERT INTO public.articles VALUES (27, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><code>a</code></p>', 39, 1, '2025-06-06 12:19:21.669594', '2025-06-06 12:19:21.669594');
INSERT INTO public.articles VALUES (28, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><u>a</u></p>', 39, 1, '2025-06-06 12:19:25.471839', '2025-06-06 12:19:25.471839');
INSERT INTO public.articles VALUES (29, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><u>a</u></p><p></p><p><u>aqwe</u></p>', 39, 1, '2025-06-06 12:19:31.701591', '2025-06-06 12:19:31.701591');
INSERT INTO public.articles VALUES (32, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><u>a</u></p><p></p><p>aqwe</p>', 39, 1, '2025-06-06 12:19:47.507264', '2025-06-06 12:19:47.507264');
INSERT INTO public.articles VALUES (33, 'Test1', '<p>a</p><p><code>a</code></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></p><p><u>a</u></p><p></p><p><u>aqwe</u></p>', 39, 1, '2025-06-06 12:19:52.180926', '2025-06-06 12:19:52.180926');
INSERT INTO public.articles VALUES (35, 'Test1', '<p><u><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></u></p>', 39, 1, '2025-06-06 12:20:05.071904', '2025-06-06 12:20:05.071904');
INSERT INTO public.articles VALUES (38, 'Test1', '<p><u><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></u></p><p>qw</p><p>qw</p><p></p>', 39, 1, '2025-06-06 12:20:20.591921', '2025-06-06 12:20:20.591921');
INSERT INTO public.articles VALUES (39, 'Test1', '<p style="text-align: center"><u><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a</mark></u></p><p>qw<sub>2</sub></p><p>qw</p><p></p>', 39, 1, '2025-06-06 12:20:26.892966', '2025-06-06 12:20:26.892966');
INSERT INTO public.articles VALUES (40, 'Test1', '<p style="text-align: center"><u><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a<sup>1</sup></mark></u></p><p>qw<sub>2</sub></p><p>qw</p><p></p>', 39, 1, '2025-06-06 12:20:30.454402', '2025-06-06 12:20:30.454402');
INSERT INTO public.articles VALUES (42, 'Test1', '<p style="text-align: center"><u><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">a<sup>1</sup></mark></u></p><p>qw<sub>2</sub></p><p>qw</p><p></p><p>qweqw</p><p></p><p>qwe</p>', 39, 1, '2025-06-06 12:20:45.056084', '2025-06-06 12:20:45.056084');
INSERT INTO public.articles VALUES (48, 'Title', '<p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p></p>', 39, 1, '2025-06-06 12:53:01.12796', '2025-06-06 12:53:01.12796');
INSERT INTO public.articles VALUES (49, 'Title', '<p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p><strong>Tell your story...</strong></p><p><code>Tell your story...</code></p><p><strong><em><s><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...</mark></s></em></strong></p><ul><li><p>Tell your story...</p></li></ul><blockquote><p>Tell your story...</p></blockquote><pre><code>Tell your story...</code></pre><p></p>', 39, 1, '2025-06-06 12:53:28.070403', '2025-06-06 12:53:28.070403');
INSERT INTO public.articles VALUES (50, 'So this is my story', '<h1>Lorem Ipsum</h1><p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</p><pre><code class="language-typescriptreact">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</code></pre><p><strong>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</strong></p><h2>Lorem Ipsum</h2><p><s><u><mark data-color="var(--tt-color-highlight-blue)" style="background-color: var(--tt-color-highlight-blue); color: inherit">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo </mark></u></s>magnam nostrum<s><u><mark data-color="var(--tt-color-highlight-blue)" style="background-color: var(--tt-color-highlight-blue); color: inherit"> voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</mark></u></s></p><p><em>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</em></p><p style="text-align: center">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam <code>temporibus aliquid! Dolor, rem quae!</code></p><p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</p><p></p>', 39, 1, '2025-06-06 15:01:55.942142', '2025-06-06 15:01:55.942142');
INSERT INTO public.articles VALUES (51, 'So this is just another color test article actually this has become a very long name apparently ', '<h1>Lorem Ipsum</h1><p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</p><p><strong><em>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</em></strong></p><p><em>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</em></p><h2>Lorem Ipsum</h2><p><em><s>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</s></em></p><p><strong><em><s>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</s></em></strong></p><h4>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</h4><h3 style="text-align: right">Lorem Ipsum</h3><p style="text-align: right">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</p><pre><code>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</code></pre><blockquote><p style="text-align: justify">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus natus impedit quo hic dolore beatae corporis explicabo magnam nostrum voluptates doloribus perspiciatis, eum, voluptate nam temporibus aliquid! Dolor, rem quae!</p></blockquote><p></p><ol><li><p>Lorem Ipsum</p></li><li><p>Lorem Ipsum</p></li><li><p>Lorem Ipsum</p></li></ol><p></p>', 39, 1, '2025-06-06 16:00:32.13564', '2025-06-06 16:00:32.13564');
INSERT INTO public.articles VALUES (52, 'Now this is my article', '<h1>Lorem Ipsum</h1><pre><code>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</code></pre><blockquote><p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</p></blockquote><h2><mark data-color="var(--tt-color-highlight-red)" style="background-color: var(--tt-color-highlight-red); color: inherit">Lorem Ipsum</mark></h2><p><strong><em><s>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</s></em></strong></p><p><mark data-color="var(--tt-color-highlight-green)" style="background-color: var(--tt-color-highlight-green); color: inherit">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</mark></p><p><mark data-color="var(--tt-color-highlight-blue)" style="background-color: var(--tt-color-highlight-blue); color: inherit">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo,</mark><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit"> culpa</mark><mark data-color="var(--tt-color-highlight-blue)" style="background-color: var(--tt-color-highlight-blue); color: inherit"> ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</mark></p><p>Lorem ipsum dolor sit amet consectetur, <strong>adipisicin</strong>g elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumqu<sub>e</sub> explicab<sup>o</sup>, culpa ducimus provident id quidem error corrupti <u>quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</u></p><p style="text-align: center"><mark data-color="var(--tt-color-highlight-yellow)" style="background-color: var(--tt-color-highlight-yellow); color: inherit">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos </mark>earum. Illo saepe quisquam explicabo.</p><p style="text-align: right">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Facilis similique quo accusantium illum nemo eaque, minus distinctio cumque explicabo, culpa ducimus provident id quidem error corrupti quibusdam labore ipsa ad quos et dignissimos earum. Illo saepe quisquam explicabo.</p><p></p>', 39, 1, '2025-06-06 20:06:22.475687', '2025-06-06 20:06:22.475687');
INSERT INTO public.articles VALUES (53, 'Testing before adding categories', '<h1>Lorem ipsum</h1><h2>Lorem ipsum</h2><p><strong><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Lorem ipsum</mark></strong></p><p><strong><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Lorem ipsum</mark></strong><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit"><br></mark></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Lorem ipsum</mark><strong><br></strong></p><p><strong>Lorem ipsum<br></strong></p><p><strong>Lorem ipsum</strong><br></p><ul><li><p>Lorem ipsum</p></li></ul><p></p>', 39, 1, '2025-06-07 09:19:15.503934', '2025-06-07 09:19:15.503934');
INSERT INTO public.articles VALUES (54, 'First try after adding categories', '<h1>Tell your story...</h1><ul><li><p>Tell your story...<br><br><code>Tell your story...</code></p></li></ul><p><br><u>Tell your story...</u><br></p><p style="text-align: center"><strong><em><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...<br></mark></em></strong></p><blockquote><p><strong><em><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit">Tell your story...<br><br>Tell your story...<br>Tell your story...</mark></em></strong></p></blockquote><p style="text-align: right">qweqww</p>', 39, 6, '2025-06-07 10:48:04.206013', '2025-06-07 10:48:04.206013');
INSERT INTO public.articles VALUES (55, 'Let''s See If We Can Get User Id Correctly', '<h1><strong>Tell your story...</strong></h1><p><strong>Tell your </strong><code>story...</code></p><p><strong><em>Tell your story...</em></strong></p><p><em><s>Tell your story...</s></em></p><p><em><s>Tell your story...<br></s></em></p><p><mark data-color="var(--tt-color-highlight-purple)" style="background-color: var(--tt-color-highlight-purple); color: inherit"><br>Tell your story...</mark></p><blockquote><p><br>Tell your story...</p></blockquote><ul><li><p><br>Tell your story...</p></li></ul><ul data-type="taskList"><li data-checked="true" data-type="taskItem"><label><input type="checkbox" checked="checked"><span></span></label><div><p>Tell your story...</p></div></li><li data-checked="false" data-type="taskItem"><label><input type="checkbox"><span></span></label><div><p>q</p></div></li><li data-checked="true" data-type="taskItem"><label><input type="checkbox" checked="checked"><span></span></label><div><p>w</p></div></li><li data-checked="false" data-type="taskItem"><label><input type="checkbox"><span></span></label><div><p>e</p></div></li><li data-checked="false" data-type="taskItem"><label><input type="checkbox"><span></span></label><div><p></p></div></li></ul><p></p><p></p>', 39, 5, '2025-06-07 11:07:03.142954', '2025-06-07 11:07:03.142954');
INSERT INTO public.articles VALUES (56, 'Some Changes Were Made', '<h1>Tell your story...</h1><h2><mark data-color="var(--tt-color-highlight-red)" style="background-color: var(--tt-color-highlight-red); color: inherit">Tell your story...</mark></h2><ol><li><p>Tell your story...<br>Tell your story...</p></li></ol><blockquote><p>Tell your story...<br>Tell your story...</p></blockquote><p><strong>Tell your story...Tell your story...Tell your story...Tell your story...</strong></p><p></p><p style="text-align: right">Tell your story...Tell your story...</p>', 38, 6, '2025-06-08 14:35:38.638093', '2025-06-08 14:35:38.638093');


--
-- TOC entry 4968 (class 0 OID 20297)
-- Dependencies: 219
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categories VALUES (1, 'Technology', 'Yazılım, donanım ve teknoloji haberleri');
INSERT INTO public.categories VALUES (2, 'Health', 'Sağlık, beslenme ve yaşam tarzı');
INSERT INTO public.categories VALUES (3, 'Travel', 'Seyahat rehberleri ve gezi notları');
INSERT INTO public.categories VALUES (4, 'Education', 'Eğitim kaynakları ve ipuçları');
INSERT INTO public.categories VALUES (5, 'Entertainment', 'Sinema, müzik ve oyun dünyası');
INSERT INTO public.categories VALUES (6, 'Finance', 'description');


--
-- TOC entry 4969 (class 0 OID 20302)
-- Dependencies: 220
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
INSERT INTO public.comment_table VALUES (12, 2, 38, 'Hello world', '2025-06-06 00:02:27');
INSERT INTO public.comment_table VALUES (13, 2, 38, 'Hello world 2', '2025-06-06 00:03:19');
INSERT INTO public.comment_table VALUES (14, 2, 38, 'Hello world3', '2025-06-06 00:03:28');
INSERT INTO public.comment_table VALUES (15, 11, 38, 'This is my comment', '2025-06-06 11:09:06');
INSERT INTO public.comment_table VALUES (16, 50, 39, 'Hello', '2025-06-06 15:47:12');
INSERT INTO public.comment_table VALUES (17, 51, 39, 'This is my comment', '2025-06-06 17:49:13');
INSERT INTO public.comment_table VALUES (18, 4, 35, 'What a great article!', '2025-06-06 20:02:54');
INSERT INTO public.comment_table VALUES (19, 53, 39, 'This is a test', '2025-06-07 09:20:07');
INSERT INTO public.comment_table VALUES (20, 54, 39, 'Well, it seems to be working', '2025-06-07 10:49:08');
INSERT INTO public.comment_table VALUES (21, 55, 39, 'This is a comment', '2025-06-07 11:07:22');
INSERT INTO public.comment_table VALUES (22, 56, 38, 'Nice!', '2025-06-08 14:35:56');


--
-- TOC entry 4971 (class 0 OID 20308)
-- Dependencies: 222
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4972 (class 0 OID 20313)
-- Dependencies: 223
-- Data for Name: followers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.followers VALUES (1, 1, 2, NULL);
INSERT INTO public.followers VALUES (2, 1, 3, NULL);
INSERT INTO public.followers VALUES (3, 2, 1, NULL);
INSERT INTO public.followers VALUES (4, 3, 4, NULL);
INSERT INTO public.followers VALUES (5, 5, 1, NULL);
INSERT INTO public.followers VALUES (4, 39, 38, NULL);
INSERT INTO public.followers VALUES (5, 1, 38, NULL);
INSERT INTO public.followers VALUES (6, 1, 39, NULL);
INSERT INTO public.followers VALUES (7, 39, 1, NULL);
INSERT INTO public.followers VALUES (8, 39, 2, NULL);
INSERT INTO public.followers VALUES (9, 39, 3, NULL);
INSERT INTO public.followers VALUES (10, 39, 4, NULL);
INSERT INTO public.followers VALUES (11, 39, 5, NULL);
INSERT INTO public.followers VALUES (12, 38, 3, NULL);
INSERT INTO public.followers VALUES (13, 38, 4, NULL);
INSERT INTO public.followers VALUES (14, 38, 1, NULL);
INSERT INTO public.followers VALUES (15, 38, 6, NULL);
INSERT INTO public.followers VALUES (16, 38, 9, NULL);
INSERT INTO public.followers VALUES (17, 38, 10, NULL);
INSERT INTO public.followers VALUES (18, 38, 7, NULL);
INSERT INTO public.followers VALUES (19, 38, 8, NULL);
INSERT INTO public.followers VALUES (20, 38, 2, NULL);
INSERT INTO public.followers VALUES (21, 39, 11, NULL);


--
-- TOC entry 4974 (class 0 OID 20317)
-- Dependencies: 225
-- Data for Name: notification_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 4975 (class 0 OID 20320)
-- Dependencies: 226
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.notifications VALUES (2, 2, 'Yorum yapıldı: Çok faydalı bir rehber...', false, '2025-04-15 08:05:00', NULL, NULL);
INSERT INTO public.notifications VALUES (3, 3, 'Sizi takip etmeye başladı: alice', true, '2025-04-15 08:10:00', NULL, NULL);
INSERT INTO public.notifications VALUES (4, 4, 'Makaleye tepki geldi: love', false, '2025-04-11 10:35:00', NULL, NULL);
INSERT INTO public.notifications VALUES (5, 5, 'Kategorinize yeni makale eklendi: 2025 Film Önerileri', false, '2025-04-14 14:05:00', NULL, NULL);
INSERT INTO public.notifications VALUES (6, 1, 'Your article got a new like!!', false, '2025-06-08 15:07:19.632617', 25, 'LOVE');
INSERT INTO public.notifications VALUES (7, 1, 'Your article got a new like!!', false, '2025-06-08 15:07:20.269436', 25, 'LOVE');
INSERT INTO public.notifications VALUES (8, 39, 'Your article got a new like!!', false, '2025-06-08 15:07:29.111823', 25, 'LOVE');
INSERT INTO public.notifications VALUES (9, 39, 'Your article got a new like!!', false, '2025-06-08 15:07:29.944778', 25, 'LOVE');
INSERT INTO public.notifications VALUES (10, 39, 'Your article got a new like!!', false, '2025-06-08 15:07:30.838904', 25, 'LOVE');
INSERT INTO public.notifications VALUES (1, 1, 'Yeni bir makale yayınlandı: Spring Boot ile REST', true, '2025-04-10 09:05:00', NULL, NULL);
INSERT INTO public.notifications VALUES (11, 39, 'Your article got a new like!!!', false, '2025-06-08 15:15:37.246524', 25, 'LOVE');


--
-- TOC entry 4977 (class 0 OID 20327)
-- Dependencies: 228
-- Data for Name: reactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.reactions VALUES (1, 1, 2, 1);
INSERT INTO public.reactions VALUES (2, 1, 3, 3);
INSERT INTO public.reactions VALUES (3, 2, 1, 5);
INSERT INTO public.reactions VALUES (4, 3, 5, 4);
INSERT INTO public.reactions VALUES (5, 5, 4, 6);
INSERT INTO public.reactions VALUES (6, 2, 8, 1);
INSERT INTO public.reactions VALUES (7, 3, 6, 1);
INSERT INTO public.reactions VALUES (8, 3, 38, 2);
INSERT INTO public.reactions VALUES (9, 1, 39, 3);
INSERT INTO public.reactions VALUES (10, 23, 39, 2);
INSERT INTO public.reactions VALUES (11, 22, 39, 3);
INSERT INTO public.reactions VALUES (12, 1, 35, 3);
INSERT INTO public.reactions VALUES (13, 2, 35, 3);
INSERT INTO public.reactions VALUES (14, 3, 35, 3);
INSERT INTO public.reactions VALUES (15, 52, 35, 4);
INSERT INTO public.reactions VALUES (16, 7, 35, 5);
INSERT INTO public.reactions VALUES (17, 53, 39, 1);
INSERT INTO public.reactions VALUES (18, 54, 39, 3);
INSERT INTO public.reactions VALUES (19, 55, 39, 3);
INSERT INTO public.reactions VALUES (20, 10, 39, 1);
INSERT INTO public.reactions VALUES (21, 56, 38, 3);
INSERT INTO public.reactions VALUES (22, 56, 39, 2);


--
-- TOC entry 4979 (class 0 OID 20331)
-- Dependencies: 230
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
INSERT INTO public.users VALUES (35, 'test6', 'test6@example.us', '$2a$10$YZHfD.uyjpv40ht1Loyoa.Q3LhVyGfxRamcAq6w6l5p81PCoDkKQ2', 'READER', '2025-06-05 16:32:30.035169');
INSERT INTO public.users VALUES (38, 'test1', 'test1@example.com', '$2a$10$gRat3pg62Yn11HUgFKKBpOwc5YJBUz1FRhHtJHYe1sFiLF/0nIzQO', 'AUTHOR', '2025-06-05 20:14:19.13029');
INSERT INTO public.users VALUES (39, 'test7', 'test7@mail.com', '$2a$10$gi/XxDKdLmKUHunfJ/qtLuOgGOkwmicop26VAHJpniBAuUuuV1DTu', 'AUTHOR', '2025-06-06 11:28:50.463202');
INSERT INTO public.users VALUES (41, 'test8', 'test8@example.us', '$2a$10$59Q8joyb.wEdUq4hjqYboOPWQcq47NRuwJf.yzRWxbYrRdzFftL4i', 'READER', '2025-06-08 23:42:16.591373');


--
-- TOC entry 4993 (class 0 OID 0)
-- Dependencies: 218
-- Name: articles_article_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.articles_article_id_seq', 56, true);


--
-- TOC entry 4994 (class 0 OID 0)
-- Dependencies: 232
-- Name: categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_category_id_seq', 6, true);


--
-- TOC entry 4995 (class 0 OID 0)
-- Dependencies: 221
-- Name: comment_table_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_table_comment_id_seq', 22, true);


--
-- TOC entry 4996 (class 0 OID 0)
-- Dependencies: 224
-- Name: followers_follower_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.followers_follower_id_seq', 21, true);


--
-- TOC entry 4997 (class 0 OID 0)
-- Dependencies: 227
-- Name: notifications_notification_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.notifications_notification_id_seq', 11, true);


--
-- TOC entry 4998 (class 0 OID 0)
-- Dependencies: 229
-- Name: reactions_reaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reactions_reaction_id_seq', 22, true);


--
-- TOC entry 4999 (class 0 OID 0)
-- Dependencies: 231
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 41, true);


--
-- TOC entry 4789 (class 2606 OID 20344)
-- Name: articles articles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (article_id);


--
-- TOC entry 4791 (class 2606 OID 20346)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 4793 (class 2606 OID 20348)
-- Name: comment_table comment_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_pkey PRIMARY KEY (comment_id);


--
-- TOC entry 4795 (class 2606 OID 20350)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (comment_id);


--
-- TOC entry 4797 (class 2606 OID 20352)
-- Name: followers followers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_pkey PRIMARY KEY (follower_user_id, user_id);


--
-- TOC entry 4799 (class 2606 OID 20354)
-- Name: notification_user notification_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT notification_user_pkey PRIMARY KEY (notification_id, user_id);


--
-- TOC entry 4801 (class 2606 OID 20356)
-- Name: notifications notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (notification_id);


--
-- TOC entry 4803 (class 2606 OID 20358)
-- Name: reactions reactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_pkey PRIMARY KEY (reaction_id);


--
-- TOC entry 4805 (class 2606 OID 20360)
-- Name: users users_passwordhash_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_passwordhash_key UNIQUE (passwordhash);


--
-- TOC entry 4807 (class 2606 OID 20362)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4808 (class 2606 OID 20363)
-- Name: articles articles_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(category_id);


--
-- TOC entry 4809 (class 2606 OID 20368)
-- Name: articles articles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4810 (class 2606 OID 20373)
-- Name: comment_table comment_table_article_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_article_id_fkey FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- TOC entry 4811 (class 2606 OID 20378)
-- Name: comment_table comment_table_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment_table
    ADD CONSTRAINT comment_table_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4816 (class 2606 OID 20383)
-- Name: notification_user fk4hgwdka35gwd4r4023bbcdvrp; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT fk4hgwdka35gwd4r4023bbcdvrp FOREIGN KEY (notification_id) REFERENCES public.notifications(notification_id);


--
-- TOC entry 4812 (class 2606 OID 20388)
-- Name: comments fk8omq0tc18jd43bu5tjh6jvraq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fk8omq0tc18jd43bu5tjh6jvraq FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4813 (class 2606 OID 20393)
-- Name: comments fkk4ib6syde10dalk7r7xdl0m5p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkk4ib6syde10dalk7r7xdl0m5p FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- TOC entry 4817 (class 2606 OID 20398)
-- Name: notification_user fkn0b60leyk6c3huiq3doc697wx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notification_user
    ADD CONSTRAINT fkn0b60leyk6c3huiq3doc697wx FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4814 (class 2606 OID 20403)
-- Name: followers followers_follower_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_follower_user_id_fkey FOREIGN KEY (follower_user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4815 (class 2606 OID 20408)
-- Name: followers followers_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followers
    ADD CONSTRAINT followers_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4818 (class 2606 OID 20413)
-- Name: notifications notifications_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4819 (class 2606 OID 20418)
-- Name: reactions reactions_article_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_article_id_fkey FOREIGN KEY (article_id) REFERENCES public.articles(article_id);


--
-- TOC entry 4820 (class 2606 OID 20423)
-- Name: reactions reactions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reactions
    ADD CONSTRAINT reactions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


-- Completed on 2025-06-09 11:05:12

--
-- PostgreSQL database dump complete
--

