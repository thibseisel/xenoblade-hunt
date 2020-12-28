package fr.nihilus.xenobladechronicles.model

private val INITIAL_MONSTERS = listOf(
    Monster(null, 5, "Bluchal le béjaune", "Xénille", Area.COLONY_9, "Plage au nord-ouest de la ville", false),
    Monster(null, 6, "Magdalena l'aquatile", "Sardi", Area.COLONY_9, "Grotte aquatique sous le parc", false),
    Monster(null, 6, "Rhandgrot le délétère", "Lapix", Area.COLONY_9, "Virage du plateau de Tephra", false),
    Monster(null, 8, "Dorothea la nomade", "Bernie", Area.COLONY_9, "Se déplace près du lac du précipice", false),
    Monster(null, 8, "Patrichiev le foreur", "Lapik", Area.TEPHRA_CAVES, "A l'est du nid de Xenilles", false),
    Monster(null, 9, "Gwynry la grimpeuse", "Squamat", Area.TEPHRA_CAVES, "A l'est de l'entrepôt 2, au bout du tunnel sans issue", false),
    Monster(null, 10, "Ramsyde la véloce", "Lapix", Area.COLONY_9, "Près du lac du précipice", false),
    Monster(null, 10, "Bugworm le creux", "Xénille", Area.TEPHRA_CAVES, "Nid de Xenilles", false),
    Monster(null, 10, "Konev le solide", "Bernie", Area.TEPHRA_CAVES, "Caverne de Tephra, devant la mare", false),
    Monster(null, 11, "Eugène le goulu", "Batraz", Area.TEPHRA_CAVES, "Lac Vilia, sur un îlot", false),
    Monster(null, 13, "Grune l'enchanteresse", "Flamy", Area.COLONY_9, "Plage près de l'entrée de la grotte de Tephra", false),
    Monster(null, 15, "Paramecia le tireur", "Rampha", Area.BIONIS_LEG, "Perchoir au sud du rocher de Jabos", false),
    Monster(null, 16, "Andante l'ardente", "Arma", Area.BIONIS_LEG, "Se déplace autour du plateau étoilé", false),
    Monster(null, 17, "Volfen le somnolent", "Volf", Area.BIONIS_LEG, "A l'est de la plaine de Gaur", false),
    Monster(null, 17, "Eduardo le nivéen", "Batraz", Area.BIONIS_LEG, "Lac de Raguel", false),
    Monster(null, 18, "Cardamon le nocturne", "Luxia", Area.BIONIS_LEG, "Lac au sud de la vallée spirale", false),
    Monster(null, 18, "Murakmor le sombre", "Tokilos", Area.COLONY_9, "Sommet de la batterie ant-aérienne C", false),
    Monster(null, 19, "Holand le gracieux", "Nebula", Area.COLONY_6, "Chemin spinescent", false),
    Monster(null, 20, "Kisling l'obscur", "Chiro", Area.ETHER_MINE, "Gisement de pyroether à l'est du Terminal central", false),
    Monster(null, 22, "Daulton le vindicatif", "Squamat", Area.ETHER_MINE, "Fond d'excavation N°4", false),
    Monster(null, 25, "Juxar l'instable", "Sciurus", Area.COLONY_6, "Parc des navettes", false),
    Monster(null, 25, "Widardun le tourmenté", "Strigid", Area.SATORL_MARSH, "Îlot à l'ouest des chutes de Zaldania", false),
    Monster(null, 26, "Balteid le vigilant", "Urodel", Area.SATORL_MARSH, "Au sud du marais empoisonné", false),
    Monster(null, 27, "Felix le furieux", "Urodel", Area.SATORL_MARSH, "Corniche à l'ouest de l'obélisque du silence", false),
    Monster(null, 27, "Figger l'ambré", "Flamy", Area.SATORL_MARSH, "Îlot au sud de l'autel du destin", false),
    Monster(null, 28, "Cornélius l'acerbe", "Urodel", Area.SATORL_MARSH, "Arbre entouré d'eau sur le Territoire des Urodel", false),
    Monster(null, 28, "Zektol le turbulent", "Volf", Area.SATORL_MARSH, "Meute près de l'obélisque de lumière", false),
    Monster(null, 29, "Marin l'élégant", "Bernie", Area.ETHER_MINE, "Lac de jade", false),
    Monster(null, 30, "Schwaik l'illuminé", "Roghul", Area.SATORL_MARSH, "Vole autour de l'obélisque du silence", false),
    Monster(null, 31, "Godwin l'insensé", "Urodel", Area.SATORL_MARSH, "Intérieur de la forteresse de l'Exil", false),
    Monster(null, 32, "Bayel l'alpin", "Araknis", Area.BIONIS_LEG, "Temple de Dashka", false),
    Monster(null, 33, "Alba le coulant", "Falkon", Area.MAKNA_FOREST, "Plage en dessous du 3ème pont", false),
    Monster(null, 33, "Forte le chatoyant", "Deinos", Area.MAKNA_FOREST, "A l'est de l'abreuvoir des Eks", false),
    Monster(null, 34, "Gragus l'ancien", "Strigid", Area.MAKNA_FOREST, "Arche des nopons", false),
    Monster(null, 34, "Bluco le cagnard", "Eluca", Area.MAKNA_FOREST, "Champ de fleurs jaunes", false),
    Monster(null, 35, "Galgaron l'opiniâtre", "Orluca", Area.MAKNA_FOREST, "Repère des houds", false),
    Monster(null, 36, "Belmo le turbulent", "Hyln", Area.ERYTH_SEA, "Récif flottant N°1", false),
    Monster(null, 37, "Vic la douce", "Arma", Area.COLONY_9, "Rive d'Agora", false),
    Monster(null, 37, "Zolos le serein", "Ek", Area.MAKNA_FOREST, "Au nord du Moulin à vent", false),
    Monster(null, 38, "Redrob l'inexpugnable", "Bernie", Area.COLONY_9, "Cap Hazai", false),
    Monster(null, 38, "Anzabi le pacifique", "Astas", Area.HIGH_ENTIA_TOMB, "Trésorerie annexe", false),
    Monster(null, 38, "Kircheis l'écorché", "Kromar", Area.ERYTH_SEA, "Récif flottant N°7", false),
    Monster(null, 38, "Jidoni le sidérant", "Nebula", Area.ERYTH_SEA, "Îlot en dessous du récif flottant N°5", false),
    Monster(null, 39, "Frengel le rogue", "Fourmik", Area.COLONY_9, "Grotte sous le plateau de Tephra", false),
    Monster(null, 39, "Bandaz le droit", "Pagul", Area.ERYTH_SEA, "Ile de la sérénité", false),
    Monster(null, 39, "Edegia l'excitée", "Laia", Area.ERYTH_SEA, "Récif flottant N°10", false),
    Monster(null, 40, "Rodriguez le souple", "Batraz", Area.COLONY_9, "Petite cache à l'est de la batterie anti-aérienne B", false),
    Monster(null, 40, "Zomar l'enfoui", "Kromar", Area.ERYTH_SEA, "Ilot du sceau de Soltana", false),
    Monster(null, 41, "Danaemos le nébuleux", "Ekidno", Area.ERYTH_SEA, "Ilot du sceau de Kathora", false),
    Monster(null, 42, "Gozra le funèbre", "Orluca", Area.ERYTH_SEA, "Plage intérieure de la Cachette des houds", false),
    Monster(null, 44, "Sonid l'éclair", "Ek", Area.ERYTH_SEA, "Sommet de la plage d'Anou", false),
    Monster(null, 45, "Gravar la brute", "Kromar", Area.MAKNA_FOREST, "Tombe du roi Agni", false),
    Monster(null, 75, "Barnabé l'énigmatique", "Araknis", Area.BIONIS_LEG, "Grotte de la caverne des vents", false),
    Monster(null, 76, "Altrich le rural", "Rampha", Area.BIONIS_LEG, "Nord de la cascade des abîmes", false),
    Monster(null, 81, "Barbarossa le veilleur", "Godrill", Area.BIONIS_LEG, "Se déplace dans la plaine de Gaur", false),
    Monster(null, 82, "Rockwell le cuirassier", "Testud", Area.BIONIS_LEG, "Coline de Viliera", false),
    Monster(null, 85, "Daulton l'inébranlable", "Godrill", Area.SATORL_MARSH, "Ruines de Soter", false),
    Monster(null, 88, "Ragoel le bizarre", "Rana", Area.ERYTH_SEA, "Sur la gauche de la plage d'Anou", false),
    Monster(null, 89, "Zagamai le sacré", "Tude", Area.ERYTH_SEA, "Ilot sous le récif flottant N°9", false),
    Monster(null, 90, "Gonzalez l'immuable", "Godrill", Area.BIONIS_LEG, "Au centre de la vallée spirale", false)
)