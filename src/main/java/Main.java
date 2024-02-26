import domain.Game;
import domain.Height;
import domain.Lines;
import domain.Members;
import error.ErrorHandler;
import strategy.PointStrategy;
import strategy.RandomPointStrategy;
import view.InputView;
import view.OutputView;

public class Main {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ErrorHandler errorHandler = new ErrorHandler();
    private static final PointStrategy pointStrategy = new RandomPointStrategy();

    public static void main(String[] args) {
        Members members = errorHandler.readUntilNoError(Main::makeMembers);
        Height height = errorHandler.readUntilNoError(Main::makeHeight);
        Game game = Game.of(members, Lines.of(members.getCount(), height, pointStrategy));
        outputView.printResult(game);
    }

    private static Members makeMembers() {
        return Members.from(inputView.read("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)"));
    }

    private static Height makeHeight() {
        return Height.from(inputView.read("최대 사다리 높이는 몇 개인가요?"));
    }
}
