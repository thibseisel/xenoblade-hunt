package fr.nihilus.xenobladechronicles;

import android.app.IntentService;
import android.arch.persistence.room.Room;
import android.content.Intent;

import fr.nihilus.xenobladechronicles.datastore.AppDatabase;
import fr.nihilus.xenobladechronicles.datastore.MonsterDao;
import fr.nihilus.xenobladechronicles.model.Area;
import fr.nihilus.xenobladechronicles.model.Monster;

public class DatabaseSetupService extends IntentService {

    private MonsterDao mDao;

    public DatabaseSetupService() {
        super("DatabaseSetupService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        mDao = db.monsterDao();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            insertPlaceholderData();
        }
    }

    private void insertPlaceholderData() {
        Monster[] monsters = new Monster[]{
                newMonster(5, "Bluchal le béjaune", "Xénille", Area.COLONY_9, "Plage au nord-ouest de la ville"),
                newMonster(6, "Magdalena l'aquatile", "Sardi", Area.COLONY_9, "Grotte aquatique sous le parc"),
                newMonster(6, "Rhandgrot le délétère", "Lapix", Area.COLONY_9, "Virage du plateau de Tephra"),
                newMonster(8, "Dorothea la nomade", "Bernie", Area.COLONY_9, "Se déplace près du lac du précipice"),
                newMonster(8, "Patrichiev le foreur", "Lapik", Area.TEPHRA_CAVES, "A l'est du nid de Xenilles"),
                newMonster(9, "Gwynry la grimpeuse", "Squamat", Area.TEPHRA_CAVES, "A l'est de l'entrepôt 2, au bout du tunnel sans issue"),
                newMonster(10, "Ramsyde la véloce", "Lapix", Area.COLONY_9, "Près du lac du précipice"),
                newMonster(10, "Bugworm le creux", "Xénille", Area.TEPHRA_CAVES, "Nid de Xenilles"),
                newMonster(10, "Konev le solide", "Bernie", Area.TEPHRA_CAVES, "Caverne de Tephra, devant la mare"),
                newMonster(11, "Eugène le goulu", "Batraz", Area.TEPHRA_CAVES, "Lac Vilia, sur un îlot"),
                newMonster(13, "Grune l'enchanteresse", "Flamy", Area.COLONY_9, "Plage près de l'entrée de la grotte de Tephra"),
                newMonster(15, "Paramecia le tireur", "Rampha", Area.BIONIS_LEG, "Perchoir au sud du rocher de Jabos"),
                newMonster(16, "Andante l'ardente", "Arma", Area.BIONIS_LEG, "Se déplace autour du plateau étoilé"),
                newMonster(17, "Volfen le somnolent", "Volf", Area.BIONIS_LEG, "A l'est de la plaine de Gaur"),
                newMonster(17, "Eduardo le nivéen", "Batraz", Area.BIONIS_LEG, "Lac de Raguel"),
                newMonster(18, "Cardamon le nocturne", "Luxia", Area.BIONIS_LEG, "Lac au sud de la vallée spirale"),
                newMonster(18, "Murakmor le sombre", "Tokilos", Area.COLONY_9, "Sommet de la batterie ant-aérienne C"),
                newMonster(19, "Holand le gracieux", "Nebula", Area.COLONY_6, "Chemin spinescent"),
                newMonster(20, "Kisling l'obscur", "Chiro", Area.ETHER_MINE, "Gisement de pyroether à l'est du Terminal central"),
                newMonster(22, "Daulton le vindicatif", "Squamat", Area.ETHER_MINE, "Fond d'excavation N°4"),
                newMonster(25, "Juxar l'instable", "Sciurus", Area.COLONY_6, "Parc des navettes"),
                newMonster(25, "Widardun le tourmenté", "Strigid", Area.SATORL_MARSH, "Îlot à l'ouest des chutes de Zaldania"),
                newMonster(26, "Balteid le vigilant", "Urodel", Area.SATORL_MARSH, "Au sud du marais empoisonné"),
                newMonster(27, "Felix le furieux", "Urodel", Area.SATORL_MARSH, "Corniche à l'ouest de l'obélisque du silence"),
                newMonster(27, "Figger l'ambré", "Flamy", Area.SATORL_MARSH, "Îlot au sud de l'autel du destin"),
                newMonster(28, "Cornélius l'acerbe", "Urodel", Area.SATORL_MARSH, "Arbre entouré d'eau sur le Territoire des Urodel"),
                newMonster(28, "Zektol le turbulent", "Volf", Area.SATORL_MARSH, "Meute près de l'obélisque de lumière"),
                newMonster(29, "Marin l'élégant", "Bernie", Area.ETHER_MINE, "Lac de jade"),
                newMonster(30, "Schwaik l'illuminé", "Roghul", Area.SATORL_MARSH, "Vole autour de l'obélisque du silence"),
                newMonster(31, "Godwin l'insensé", "Urodel", Area.SATORL_MARSH, "Intérieur de la forteresse de l'Exil"),
                newMonster(32, "Bayel l'alpin", "Araknis", Area.BIONIS_LEG, "Temple de Dashka"),
                newMonster(33, "Alba le coulant", "Falkon", Area.MAKNA_FOREST, "Plage en dessous du 3ème pont"),
                newMonster(33, "Forte le chatoyant", "Deinos", Area.MAKNA_FOREST, "A l'est de l'abreuvoir des Eks"),
                newMonster(34, "Gragus l'ancien", "Strigid", Area.MAKNA_FOREST, "Arche des nopons"),
                newMonster(34, "Bluco le cagnard", "Eluca", Area.MAKNA_FOREST, "Champ de fleurs jaunes"),
                newMonster(35, "Galgaron l'opiniâtre", "Orluca", Area.MAKNA_FOREST, "Repère des houds"),
                newMonster(36, "Belmo le turbulent", "Hyln", Area.ERYTH_SEA, "Récif flottant N°1"),
                newMonster(37, "Vic la douce", "Arma", Area.COLONY_9, "Rive d'Agora"),
                newMonster(37, "Zolos le serein", "Ek", Area.MAKNA_FOREST, "Au nord du Moulin à vent"),
                newMonster(38, "Redrob l'inexpugnable", "Bernie", Area.COLONY_9, "Cap Hazai"),
                newMonster(38, "Anzabi le pacifique", "Astas", Area.HIGH_ENTIA_TOMB, "Trésorerie annexe"),
                newMonster(38, "Kircheis l'écorché", "Kromar", Area.ERYTH_SEA, "Récif flottant N°7"),
                newMonster(38, "Jidoni le sidérant", "Nebula", Area.ERYTH_SEA, "Îlot en dessous du récif flottant N°5"),
                newMonster(39, "Frengel le rogue", "Fourmik", Area.COLONY_9, "Grotte sous le plateau de Tephra"),
                newMonster(39, "Bandaz le droit", "Pagul", Area.ERYTH_SEA, "Ile de la sérénité"),
                newMonster(39, "Edegia l'excitée", "Laia", Area.ERYTH_SEA, "Récif flottant N°10"),
                newMonster(40, "Rodriguez le souple", "Batraz", Area.COLONY_9, "Petite cache à l'est de la batterie anti-aérienne B"),
                newMonster(40, "Zomar l'enfoui", "Kromar", Area.ERYTH_SEA, "Ilot du sceau de Soltana"),
                newMonster(41, "Danaemos le nébuleux", "Ekidno", Area.ERYTH_SEA, "Ilot du sceau de Kathora"),
                newMonster(42, "Gozra le funèbre", "Orluca", Area.ERYTH_SEA, "Plage intérieure de la Cachette des houds"),
                newMonster(44, "Sonid l'éclair", "Ek", Area.ERYTH_SEA, "Sommet de la plage d'Anou"),
                newMonster(45, "Gravar la brute", "Kromar", Area.MAKNA_FOREST, "Tombe du roi Agni"),
                newMonster(75, "Barnabé l'énigmatique", "Araknis", Area.BIONIS_LEG, "Grotte de la caverne des vents"),
                newMonster(76, "Altrich le rural", "Rampha", Area.BIONIS_LEG, "Nord de la cascade des abîmes"),
                newMonster(81, "Barbarossa le veilleur", "Godrill", Area.BIONIS_LEG, "Se déplace dans la plaine de Gaur"),
                newMonster(82, "Rockwell le cuirassier", "Testud", Area.BIONIS_LEG, "Coline de Viliera"),
                newMonster(85, "Daulton l'inébranlable", "Godrill", Area.SATORL_MARSH, "Ruines de Soter"),
                newMonster(88, "Ragoel le bizarre", "Rana", Area.ERYTH_SEA, "Sur la gauche de la plage d'Anou"),
                newMonster(89, "Zagamai le sacré", "Tude", Area.ERYTH_SEA, "Ilot sous le récif flottant N°9"),
                newMonster(90, "Gonzalez l'immuable", "Godrill", Area.BIONIS_LEG, "Au centre de la vallée spirale")
        };

        mDao.insert(monsters);
    }

    private Monster newMonster(int level, String name, String kind, Area area, String location) {
        Monster monster = new Monster();
        monster.setLevel(level);
        monster.setName(name);
        monster.setKind(kind);
        monster.setArea(area);
        monster.setLocation(location);
        return monster;
    }
}
