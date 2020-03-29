package mathpar.web.learning.gateway.utils;

public class SecurityUtils {
    public static final String AUTHENTICATION_TOKEN_NAME = "AUTH-TOKEN";
    public static String[] whitelistUrls = new String[]{"/*", "/assets/**", "/api/authentication/authenticate", "/api/restoration/**", "/api/school/register", "/v2/api-docs", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**"};

//    public static long extractUserId(String token){
//        try {
//            return Long.parseLong(token.substring(0, token.indexOf("_")));
//        }catch (NumberFormatException e){
//            throw new MalformedDataException("AuthenticationToken is invalid");
//        }
//    }
//
//    public static InstitutionType extractInstitutionType(String token){
//        try {
//            return InstitutionType.of(token.substring(token.indexOf("_")+1, token.indexOf("/")));
//        }catch (NumberFormatException e){
//            throw new MalformedDataException("AuthenticationToken is invalid");
//        }
//    }

//    public static UserAuthentication getUserAuthentication(){
//        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
//    }
}
