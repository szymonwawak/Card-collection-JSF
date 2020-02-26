SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `card` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `cost` int(11) NOT NULL,
  `attack` int(11) NOT NULL,
  `health` int(11) NOT NULL,
  `rarity` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fraction` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `scraps_cost` int(11) NOT NULL,
  `scraps_earned` int(11) NOT NULL,
  `filename` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `card` (`id`, `name`, `description`, `cost`, `attack`, `health`, `rarity`, `fraction`, `scraps_cost`, `scraps_earned`, `filename`, `created_at`, `updated_at`) VALUES
(2, 'Donald Zdobywca', 'Rozmieszczenie: Wygnaj dwie karty przeciwnika na emigrację', 15, 8, 8, 'Legendarna', 'Opozycja', 1600, 400, 'default.png', '2019-12-03 06:32:03', '2019-12-03 06:32:03'),
(3, 'Jarosław Van Damme', 'Szarża. Gdy przeciwnik zagra stronnika, zaatakuj go', 15, 8, 8, 'Legendarna', 'Rząd', 1600, 400, 'default.png', '2019-12-03 06:32:03', '2019-12-03 06:32:03'),
(46, 'Janusz: Szalony Tytan', 'Rozmieszczenie: Zniszcz całą rękę przeciwnika', 20, 5, 5, NULL, 'Fraction 3', 1600, 400, 'default.png', NULL, NULL),
(47, 'Zbigniew z Warszawy', 'Okrzyk bojowy: zniszcz stronnika (cel wybrany losowo)', 3, 5, 5, NULL, 'Fraction 3', 100, 20, 'default.png', NULL, NULL),
(48, 'Ewa', 'Okrzyk bojowy: Otrzymuje +2 do ataku i zryw jeżeli przeciwnik kontroluje dinozaura', 5, 5, 5, NULL, 'Fraction 1', 100, 20, 'default.png', NULL, NULL),
(49, 'Rysiek', 'Prowokacja. Zniszcz na początku twojej tury', 2, 4, 4, NULL, 'Fraction 1', 40, 5, 'default.png', NULL, NULL),
(50, 'Antonii: Wynalazca', 'Okrzyk bojowy: Daj wszystkim sojusznikom +2/+2', 7, 5, 5, NULL, 'Fraction 2', 400, 100, 'default.png', NULL, NULL),
(51, 'Agent Bolek', 'Prowokacja, boska tarcza. Jeżeli kontrolujesz stronnika z KODu otrzymuje +2/+2', 6, 5, 6, NULL, 'Fraction 1', 400, 100, 'default.png', NULL, NULL),
(52, 'Harnaś', 'Zabierz każdemu Stronnikowi 1/1 i dodaj do swoich statystyk', 8, 4, 4, NULL, 'Fraction 2', 400, 100, 'default.png', NULL, NULL);

CREATE TABLE `card_proposition` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `cost` int(11) NOT NULL,
  `attack` int(11) NOT NULL,
  `health` int(11) NOT NULL,
  `rarity` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fraction` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `scraps_cost` int(11) NOT NULL,
  `scraps_earned` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `role` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `role` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'user', '2019-12-03 06:32:03', '2019-12-03 06:32:03'),
(3, 'admin', '2019-12-03 06:32:03', '2019-12-03 06:32:03');

CREATE TABLE `statistics` (
  `id` bigint(11) UNSIGNED NOT NULL,
  `cards_created` int(11) NOT NULL,
  `cards_destroyed` int(11) NOT NULL,
  `scraps_used` int(11) NOT NULL,
  `scraps_earned` int(11) NOT NULL,
  `collection_value` int(11) NOT NULL,
  `cardscraps` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `statistics` (`id`, `cards_created`, `cards_destroyed`, `scraps_used`, `scraps_earned`, `collection_value`, `cardscraps`) VALUES
(33, 0, 0, 0, 0, 0, 5000),
(34, 0, 0, 0, 0, 0, 5000);

CREATE TABLE `user` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `cardscraps` int(10) UNSIGNED NOT NULL DEFAULT 5000,
  `avatar` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `statistics_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user` (`id`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`, `cardscraps`, `avatar`, `statistics_id`) VALUES
(51, 'user', 'user@user.pl', NULL, 'EE11CBB19052E40B07AAC0CA060C23EE', NULL, '2020-02-26 15:20:13', NULL, 5000, 'default-avatar.jpg', 33),
(52, 'admin', 'admin@admin.pl', NULL, '21232F297A57A5A743894A0E4A801FC3', NULL, '2020-02-26 15:20:34', NULL, 5000, 'default-avatar.jpg', 34);

CREATE TABLE `user_card` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `card_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user_card` (`id`, `user_id`, `card_id`, `created_at`, `updated_at`) VALUES
(453, 51, 46, NULL, NULL),
(454, 51, 48, NULL, NULL),
(455, 51, 51, NULL, NULL),
(456, 51, 3, NULL, NULL),
(457, 51, 50, NULL, NULL),
(458, 51, 47, NULL, NULL),
(459, 52, 3, NULL, NULL),
(460, 52, 49, NULL, NULL),
(461, 52, 2, NULL, NULL),
(462, 52, 46, NULL, NULL),
(463, 52, 47, NULL, NULL),
(464, 52, 50, NULL, NULL);

CREATE TABLE `user_role` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `role_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `created_at`, `updated_at`) VALUES
(36, 51, 1, NULL, NULL),
(37, 52, 1, NULL, NULL),
(39, 52, 3, NULL, NULL);

ALTER TABLE `card`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `card_proposition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `statistics`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_name_unique` (`name`),
  ADD UNIQUE KEY `users_email_unique` (`email`),
  ADD KEY `statistics_id` (`statistics_id`);

ALTER TABLE `user_card`
  ADD PRIMARY KEY (`id`),
  ADD KEY `collection_user_id_foreign` (`user_id`),
  ADD KEY `collection_card_id_foreign` (`card_id`);

ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_roles_user_id_foreign` (`user_id`),
  ADD KEY `user_roles_role_id_foreign` (`role_id`);

ALTER TABLE `card`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

ALTER TABLE `card_proposition`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

ALTER TABLE `role`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `statistics`
  MODIFY `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

ALTER TABLE `user`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

ALTER TABLE `user_card`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=471;

ALTER TABLE `user_role`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

ALTER TABLE `card_proposition`
  ADD CONSTRAINT `card_proposition_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`statistics_id`) REFERENCES `statistics` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_card`
  ADD CONSTRAINT `collection_card_id_foreign` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  ADD CONSTRAINT `collection_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_role`
  ADD CONSTRAINT `user_roles_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `user_roles_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;