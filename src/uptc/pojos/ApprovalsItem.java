package uptc.pojos;

import lombok.Getter;

@Getter
public class ApprovalsItem{
	private Submitter submitter;
	private int submissionId;
	private String targetAudience;
	private String submissionObject;
	private int reviewedAt;
	private String submissionOutcome;
	private SubmissionDetails submissionDetails;
	private String state;
	private boolean reviewedAutomatically;
	private int submittedAt;
	private int workflowId;
	private SubmissionOutcomeApplication submissionOutcomeApplication;

}
