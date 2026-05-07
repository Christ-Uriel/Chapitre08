Objectif de l'exercice
L'objectif était de transformer le système de stockage pour utiliser des objets Book au lieu de simples chaînes de caractères et d'implémenter un tri automatique par date de publication.

 Modifications réalisées
Classe Book : Implémentation de l'interface Comparable<Book> pour définir le tri par défaut sur l'attribut publishedOn (date de publication).

Classe BookShelf : Mise à jour de la logique de stockage et utilisation de Comparator.naturalOrder() pour appliquer le tri chronologique.

Tests (BookShelfSpec) :

Initialisation des livres avec des dates réelles (1975, 2004, 2008).

Ajout du cas de test shelfSortedByPublicationDate() pour valider le bon fonctionnement du tri.

Correction des anciens tests pour s'adapter à la nouvelle structure d'objets.

 Résultats des tests
Nombre de tests : 8

Statut : ✅Tous les tests passent avec succès.

 Auteur
Nom : ADJE Christ Uriel