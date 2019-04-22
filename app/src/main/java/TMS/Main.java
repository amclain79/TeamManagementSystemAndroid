package TMS;

import android.app.Application;

import controller.CreateProfileController;
import controller.CreateProjectController;
import controller.CreateTeamController;
import controller.JoinTeamController;
import controller.LoginController;
import controller.QueryController;
import gateway.ProjectStateManager;
import interactor.PersonInteractor;
import interactor.UserInteractor;

public class Main extends Application {
    //Person
    public static String email;
    public static PersonInteractor personInteractor = new PersonInteractor(ProjectStateManager.getInstance());
    public static LoginController loginController = new LoginController(personInteractor);

    //User
    public static UserInteractor userInteractor = new UserInteractor(ProjectStateManager.getInstance());
    public static CreateProjectController createProjectController = new CreateProjectController(userInteractor);
    public static CreateProfileController createProfileController = new CreateProfileController(userInteractor);
    public static CreateTeamController createTeamController = new CreateTeamController(userInteractor);
    public static JoinTeamController joinTeamController = new JoinTeamController(userInteractor);
    public static QueryController queryController = new QueryController(userInteractor);
}
