public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5 ; i++) {
            CRUD.createItem( new Item(STR."coisa \{i}") );
        }
        System.out.println( "TODAS COISAS:" );
        System.out.println( CRUD.readAllItens() );

        System.out.println( "COISA 4:" );
        Item coisa = CRUD.readItem( "coisa 4" );
        System.out.println( coisa );

        CRUD.updateItem( new Item( coisa.getId() , "otaCoisa" ) );
        System.out.println( "NOVO" );
        System.out.println( CRUD.readItem( coisa.getId() ) );

        for ( Item item : CRUD.readAllItens() ) {
            CRUD.deleteItem( item.getId() );
        }
    }
}
