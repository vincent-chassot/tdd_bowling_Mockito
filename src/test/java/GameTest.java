import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

class GameTest {

    private Game game;
    private TableauAffichage tableauAffichage = Mockito.mock(TableauAffichage.class);

    @BeforeEach
    void setUp() {
        game = new Game(tableauAffichage);
    }

    @Test
    @DisplayName("20 rolls at 0 should be 0")
    void score_Given20RollsAt0_ShouldBe0() {
        rollMany(20,0);
        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("20 rolls at 1 should be 20")
    void score_Given20RollsAt1_ShouldBe20() {
        rollMany(20, 1);
        assertThat(game.score()).isEqualTo(20);
    }

    @Test
    @DisplayName("1 spare, then 3 pins and 17 rolls at 0 should be 16")
    void score_GivenOneSpareAnd3Pins_ShouldBe16() {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        rollMany(17, 0);
        assertThat(game.score()).isEqualTo(16);
    }

    @Test
    @DisplayName("1 strike, then 3 and 4 pins and 16 rolls at 0 should be 24")
    void score_GivenOneStrikeAnd7Pins_ShouldBe24() {
        game.roll(10);
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    @DisplayName("12 strikes should be 300")
    void score_GivenPerfectGame_ShouldBe300() {
        rollMany(12, 10);
        assertThat(game.score()).isEqualTo(300);
    }

    private void rollMany(int nb, int pins) {
        for(int i=0;i<nb;i++) {
            game.roll(pins);
        }
    }

    @Test
    @DisplayName("connect ok au tableau d'affichage 1x")
    void connection_GivenConnectionTableau_ShouldBe1(){
        Mockito.verify(tableauAffichage,times(1))
                .seConnecter();
    }

}