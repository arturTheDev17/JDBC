package classes;

import classes.banco.ClubeCRUD;
import classes.banco.JogadorCRUD;
import classes.banco.LigaCRUD;
import classes.banco.TreinadorCRUD;

import java.util.*;

public class Executavel {
    
    private static Scanner sc = new Scanner( System.in );
    
    public static void main(String[] args) {
        int escolha;

        do {
        System.out.print("""
                ==== SISTEMA DE GESTÃO DE CLUBES ESPORTIVOS ====
                1. Gerenciar Clubes
                2. Gerenciar Treinadores
                3. Gerenciar Jogadores
                4. Gerenciar Ligas
                5. Sair
                Escolha uma opção-""" );
            escolha = tryNum();
            switch ( escolha ) {
                case 1 : escolhasClubes(); break;
                case 2 : escolhasTreinadores(); break;
                case 3 : escolhasJogadores(); break;
                case 4 : escolhasLigas(); break;
                case 5 : break;
                default: System.out.println( "Valor inválido" );
            }
        } while (escolha != 5);
    }

    private static void escolhasLigas() {
        int escolha;
        do {
            System.out.println("""
                    ==== GERENCIAR LIGAS ====
                    1. Cadastrar nova liga
                    2. Listar todas as ligas
                    3. Atualizar dados de uma liga
                    4. Excluir uma liga
                    5. Listar clubes em uma liga
                    6. Voltar ao menu principal
                    Escolha uma opção: """);

            escolha = tryNum();
            switch (escolha) {
                case 1: {
                    try {
                        Liga liga = novaLiga();
                        LigaCRUD.createLiga(liga);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 2:
                    System.out.println(LigaCRUD.readAllLigas());
                    break;

                case 3: {
                    System.out.println(LigaCRUD.readAllLigas());
                    System.out.println("Insira o id da liga que deseja editar: ");
                    try {
                        int idLiga = tryNum();
                        LigaCRUD.readLiga(idLiga);
                        Liga liga = novaLiga();
                        liga.setIdLiga(idLiga);
                        LigaCRUD.updateLiga(liga);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                }
                break;

                case 4: {
                    System.out.println(LigaCRUD.readAllLigas());
                    System.out.println("Insira o id da liga que deseja remover:");
                    int id = tryNum();
                    LigaCRUD.removeLiga(id);
                }
                break;

                case 5: {
                    System.out.println("Insira o nome da liga: ");
                    sc.nextLine();
                    String ligaNome = sc.nextLine();
                    try {
                        Liga liga = LigaCRUD.readLiga(ligaNome);
                        List<Clube> clubes = LigaCRUD.readClubesLiga(liga.getIdLiga());
                        System.out.println("Clubes da liga " + ligaNome + ":");
                        liga.setClubes(clubes);
                        System.out.println(liga.getClubes());
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6:
                    break;
                default:
                    System.out.println("Valor inválido");
            }
        } while ( escolha != 6 );
    }



    private static void escolhasJogadores() {
        int escolha;
        do {
        System.out.println("""
                ==== GERENCIAR JOGADORES ====
                1. Cadastrar novo jogador
                2. Listar todos os jogadores
                3. Listar jogadores de um clube
                4. Atualizar dados de um jogador
                5. Excluir um jogador
                6. Voltar ao menu principal
                Escolha uma opção: """ );

        escolha = tryNum();
        switch ( escolha ) {
            case 1 : {
                try {
                    Jogador jogador = novoJogador();
                    JogadorCRUD.createJogador( jogador );
                } catch ( RuntimeException e ) {
                    System.out.println( e.getMessage() );
                };
            } break;
            case 2 : System.out.println( JogadorCRUD.readAllJogadores() ); break;
            case 3 : {
                System.out.println( "Insira o nome do clube: " );
                sc.nextLine();
                String clubeNome = sc.nextLine();
                try {
                    Clube clube = ClubeCRUD.readClube( clubeNome );
                    List<Jogador> jogadores = ClubeCRUD.readJogadoresClube( clube.getIdClube() );
                    clube.setJogadores( jogadores );
                    System.out.println( "Jogadores do clube " + clubeNome + ":" );
                    System.out.println( clube.getJogadores() );

                } catch ( RuntimeException e ) {
                    System.out.println( e.getMessage() );
                }
            } break;
            case 4 : {
                System.out.println( JogadorCRUD.readAllJogadores() );
                System.out.println( "Insira o id do jogador que deseja editar: " );
                int idJogador = tryNum();
                try {
                    JogadorCRUD.readJogador( idJogador );
                    Jogador jogador = novoJogador();
                    jogador.setIdJogador( idJogador );
                    JogadorCRUD.updateJogador( jogador );
                } catch ( RuntimeException e ) {
                    System.out.println( e.getMessage() );
                }
            } break;
            case 5 : {
                System.out.println( JogadorCRUD.readAllJogadores() );
                System.out.println( "Insira o id do jogador que deseja remover:" );
                int id = tryNum();
                JogadorCRUD.removeJogador( id );
            }break;
            case 6 : break;
            default: System.out.println( "Valor inválido" );
        }
        } while ( escolha != 6 );
    }

    private static void escolhasTreinadores() {
        int escolha;
        do {
            System.out.println("""
                    ==== GERENCIAR TREINADORES ====
                    1. Cadastrar novo treinador
                    2. Listar todos os treinadores
                    3. Atualizar dados de um treinador
                    4. Excluir um treinador
                    5. Voltar ao menu principal
                    Escolha uma opção: """ );

            escolha = tryNum();
            switch ( escolha ) {
                case 1 : {
                    try {
                        Treinador treinador = novoTreinador();
                        TreinadorCRUD.createTreinador( treinador );
                    } catch ( RuntimeException e ) {
                        System.out.println( e.getMessage() );
                    };
                } break;
                case 2 : System.out.println( TreinadorCRUD.readAllTreinadores() ); break;
                case 3 : {
                    System.out.println( TreinadorCRUD.readAllTreinadores() );
                    System.out.println( "Insira o id do treinador que deseja editar: " );
                    int idTreinador = tryNum();
                    try {
                        TreinadorCRUD.readTreinador( idTreinador );
                        Treinador treinador = novoTreinador();
                        treinador.setIdTreinador( idTreinador );
                        TreinadorCRUD.updateTreinador( treinador );
                    } catch ( RuntimeException e ) {
                        System.out.println( e.getMessage() );
                    }

                } break;
                case 4 : {
                    System.out.println( TreinadorCRUD.readAllTreinadores() );
                    System.out.println( "Insira o id do treinador que deseja remover:" );
                    int id = tryNum();
                    TreinadorCRUD.removeTreinador( id );
                }break;
                case 5 : break;
                default: System.out.println( "Valor inválido" );
            }
        } while ( escolha != 5 );
    }

    private static void escolhasClubes() {
        int escolha;
        do {
            System.out.println("""
                    ==== GERENCIAR CLUBES ====
                    1. Cadastrar novo clube
                    2. Listar todos os clubes
                    3. Atualizar dados de um clube
                    4. Excluir um clube
                    5. Gerenciar ligas do clube
                    6. Voltar ao menu principal
                    Escolha uma opção: """ );
            escolha = tryNum();
            switch ( escolha ) {
                case 1 : ClubeCRUD.createClube(novoClube());; break;
                case 2 : System.out.println( ClubeCRUD.readAllClubes() ); break;
                case 3 : {
                    System.out.println( ClubeCRUD.readAllClubes() );
                    System.out.println( "Insira o id do clube que deseja editar: " );
                    try {
                        int idClube = tryNum();
                        ClubeCRUD.readClube( idClube );
                        Clube clube = novoClube();
                        clube.setIdClube( idClube );
                        ClubeCRUD.updateClube( clube );
                    } catch ( RuntimeException e ) {
                        System.out.println( e.getMessage() );
                    }

                } break;
                case 4 : {
                    System.out.println( ClubeCRUD.readAllClubes() );
                    System.out.println( "Insira o id do clube que deseja remover:" );
                    int id = tryNum();
                    ClubeCRUD.removeClube( id );
                }break;
                case 5 : {
                    System.out.println( ClubeCRUD.readAllClubes() );
                    System.out.println( "Insira o id do clube que deseja gerenciar" );
                    try {
                        Clube clube = ClubeCRUD.readClube( tryNum() );
                        int escolhaLiga;
                        do {
                            System.out.println("""
                            ==== GERENCIAR LIGAS DO CLUBE ====
                            1. Listar ligas associadas ao clube
                            2. Adicionar clube a uma liga
                            3. Remover clube de uma liga
                            4. Voltar ao menu anterior
                            Escolha uma opção: """ );
                            escolhaLiga = tryNum();
                            switch ( escolhaLiga ) {
                                case 1 : System.out.println(clube.getLigas()); break;
                                case 2 : {
                                    System.out.println( LigaCRUD.readAllLigas() );
                                    System.out.println( "Digite o nome da liga que deseja adicionar." );
                                    sc.nextLine();
                                    String nomeLiga = sc.nextLine();
                                    try {
                                        Liga liga = LigaCRUD.readLiga( nomeLiga );
                                        clube.getLigas().add( liga );
                                        LigaCRUD.relacionarClubeLiga( liga.getIdLiga() , clube.getIdClube() );
                                    } catch ( RuntimeException e ) {
                                        System.out.println( e.getMessage() );
                                    }
                                } break;
                                case 3 : {
                                    System.out.println(clube.getLigas());
                                    System.out.println( "Digite o nome da liga que deseja remover." );
                                    sc.nextLine();
                                    String nomeLiga = sc.nextLine();
                                    try {
                                        for ( Liga liga : clube.getLigas() ) {
                                             if ( liga.getNome().equals( nomeLiga ) ) {
                                                 clube.getLigas().remove( liga );
                                                 LigaCRUD.removerRelacaoClubeLiga( liga.getIdLiga() , clube.getIdClube() );
                                             }
                                        }
                                    } catch ( RuntimeException e ) {
                                        System.out.println( e.getMessage() );
                                    }
                                } break;
                                case 4 : break;
                                default: System.out.println( "Valor inválido" );
                            }
                        } while ( escolhaLiga != 4 );
                    } catch ( RuntimeException e ) {
                        System.out.println( "Clube nao existe" );
                    }
                    int escolhaLiga;
                }break;
                case 6 : break;
                default: System.out.println( "Valor inválido" );
            }
        } while ( escolha != 6 );
    }

    private static Liga novaLiga() {
        sc.nextLine();
        System.out.print( "Insira o nome da liga: " );
        String nome = sc.nextLine();
        System.out.print( "Insira o ano de fundacao da liga:" );
        int anoFundacao = sc.nextInt();
        return new Liga( nome , anoFundacao );
    }

    private static Treinador novoTreinador() {
        sc.nextLine();
        System.out.print( "Insira o nome do treinador: " );
        String nome = sc.nextLine();
        System.out.print( "Insira a experiência do treinador em anos:" );
        int experiencia = sc.nextInt();
        System.out.println( "Insira o nome do clube atual do treinador" );
        try {
            sc.nextLine();
            Clube clube = ClubeCRUD.readClube( sc.nextLine() );
            return new Treinador( nome , clube , experiencia  );
        } catch ( RuntimeException e ) {
            throw e;
        }
    }

    private static Clube novoClube() {
        sc.nextLine();
        System.out.print( "Insira o nome do clube: " );
        String nome = sc.nextLine();
        System.out.print( "Insira a data de fundacao do clube:" );
        String dataFundacao = sc.nextLine();

        return new Clube( nome , dataFundacao );
    }

    private static Jogador novoJogador() {
        sc.nextLine();
        System.out.print( "Insira o nome do jogador: " );
        String nome = sc.nextLine();
        System.out.println( "Insira a posicao do jogador: " );
        String posicao = sc.nextLine();
        System.out.print( "Insira a idade do jogador:" );
        int idade = tryNum();
        System.out.println( "Insira o nome do clube atual do jogador" );
        try {
            sc.nextLine();
            Clube clube = ClubeCRUD.readClube( sc.nextLine() );
            return new Jogador( clube , idade , nome , posicao  );
        } catch ( RuntimeException e ) {
            throw e;
        }
    }

    private static int tryNum() {
        int numero = 0;
        boolean erro = true;
        while ( erro ) {
            try {
                numero = sc.nextInt();
                erro = false;
            } catch (NoSuchElementException e) {
                System.out.println("Valor inválido. ");
                sc.nextLine();
            }
        }
        return numero;
    }
}
