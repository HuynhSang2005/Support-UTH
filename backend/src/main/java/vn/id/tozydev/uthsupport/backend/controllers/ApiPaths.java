package vn.id.tozydev.uthsupport.backend.controllers;

public final class ApiPaths {
  public static final String TICKETS_ONLY = "tickets";
  public static final String COMMENTS_ONLY = "comments";
  public static final String REGISTER_ONLY = "register";
  public static final String LOGIN_ONLY = "login";

  public static final String USERNAME_PARAM = "{username}";
  public static final String TICKET_ID_PARAM = "{ticketId}";
  public static final String CATEGORY_ID_PARAM = "{categoryId}";
  public static final String COMMENT_ID_PARAM = "{commentId}";

  public static final String PREFIX = "/api/v1/";
  public static final String USERS = PREFIX + "users";
  public static final String TICKETS = PREFIX + TICKETS_ONLY;
  public static final String TICKET_COMMENT = TICKETS + "/" + TICKET_ID_PARAM + "/" + COMMENTS_ONLY;
  public static final String TICKET_STATUS = TICKET_ID_PARAM + "/status";
  public static final String AUTH = PREFIX + "auth";
  public static final String CATEGORIES = PREFIX + "categories";
  public static final String CATEGORY_ASSIGNEES = CATEGORY_ID_PARAM + "/assignees";
  public static final String COMMENTS = PREFIX + COMMENTS_ONLY;
  public static final String USER = PREFIX + "user";
}
