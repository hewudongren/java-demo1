package cn.jwis.qualityworkflow.modules.traceablity.TimerManager;

//@Component
//@EnableScheduling
public class TraceabilityEnvironmentTimerManager {
//
//	private static final Logger logger = LoggerFactory.getLogger(TraceabilityEnvironmentTimerManager.class);
//	
//    @Autowired
//    TraceabilityEnvironmentServiceImpl traceabilityEnvironmentServiceImpl;

//    @Scheduled (cron = "0 */10 * * * ?")
//    public void TimerManager()throws Exception {
//    	logger.info("环境定时任务开启");
//        JSONObject jsonObject= new JSONObject();
//        //发送 POST请求
//        String sr = doPost("http://10.114.113.113/Thingworx/Things/Lenovo_Dashboard1/Services/humiture?appKey=cf764387-eeff-42e3-bb4a-29d591b889bb", jsonObject);
//        JSONObject jsonObject1 =JSONObject.parseObject(sr);
//        JSONArray jsonArray = jsonObject1.getJSONArray("rows");
//        for (int i = 0; i <jsonArray.size() ; i++) {
//            JSONObject jsonObject2=jsonArray.getJSONObject(i);
//            String resultStr = jsonObject2.getString("result");
//            JSONArray result = JSONArray.parseArray(resultStr);
//            for (int j = 0; j <result.size() ; j++) {
//                JSONObject jsonObject3 = result.getJSONObject(j);
//                TraceabilityEnvironmentData bean =new TraceabilityEnvironmentData();
//                bean.setTemperature(jsonObject3.get("temperature").toString());
//                bean.setHumidity(jsonObject3.get("humidity").toString());
//                bean.setPosition(jsonObject3.getString("position"));
//                traceabilityEnvironmentServiceImpl.insertTraceabilityEnvironment(bean);
//            }
//        }
//    }

	/**
	 * post请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
//    public static String doPost(String url, JSONObject json) throws  Exception{
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 设置通用请求的属性
//            URLConnection conn = realUrl.openConnection();
//            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("connection", "keep-Alive");
//            conn.setRequestProperty("Content-Type", "application/json");
//           // conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 我加的一个头
//            conn.setRequestProperty("Host", "<calculated when request is sent>");
//
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(json);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String line;
//            try {
//                while ((line = in.readLine()) != null) {
//                    result += line;
//
//                }
//            } catch (IOException e) {
//            	logger.error("发送POST请求出现异常");
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            if (out != null) {
//                out.close();
//            }
//            if (in != null) {
//                in.close();
//            }
//        }
//        return result;
//    }
}
