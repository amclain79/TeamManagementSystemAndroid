package TMS;

import android.app.Application;

import controller.AssignTeamLeadController;
import controller.AssignTeamTaskController;
import controller.CreateProfileController;
import controller.CreateProjectController;
import controller.CreateTeamController;
import controller.CreateTeamFeedbackController;
import controller.JoinTeamController;
import controller.LoginController;
import controller.NominateLeadController;
import controller.QueryController;
import controller.ViewProfileController;
import controller.ViewTeamFeedbacksController;
import controller.ViewTeamTaskController;
import gateway.ProjectStateManager;
import interactor.LeadInteractor;
import interactor.ManagerInteractor;
import interactor.MemberInteractor;
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

    //Manager
    public static ManagerInteractor managerInteractor = new ManagerInteractor(ProjectStateManager.getInstance());
    public static AssignTeamTaskController assignTeamTaskController = new AssignTeamTaskController(managerInteractor);
    public static AssignTeamLeadController assignTeamLeadController = new AssignTeamLeadController(managerInteractor);
    public static ViewTeamFeedbacksController viewTeamFeedbacksController = new ViewTeamFeedbacksController(managerInteractor);

    //Lead
    public static LeadInteractor leadInteractor = new LeadInteractor(ProjectStateManager.getInstance());
    public static ViewTeamTaskController viewTeamTaskController = new ViewTeamTaskController(leadInteractor);
    public static CreateTeamFeedbackController createTeamFeedbackController = new CreateTeamFeedbackController(leadInteractor);

    //Member
    public static MemberInteractor memberInteractor = new MemberInteractor(ProjectStateManager.getInstance());
    public static NominateLeadController nominateLeadController = new NominateLeadController(memberInteractor);
    public static ViewProfileController viewProfileController = new ViewProfileController(memberInteractor);
}
