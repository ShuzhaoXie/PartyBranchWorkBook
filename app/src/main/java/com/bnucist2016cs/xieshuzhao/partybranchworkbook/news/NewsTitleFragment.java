package com.bnucist2016cs.xieshuzhao.partybranchworkbook.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.base.News;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.Book;
import com.bnucist2016cs.xieshuzhao.partybranchworkbook.data.BookAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.b.I;

public class NewsTitleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNewsList());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText = view.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<News> newsList){
            mNewsList = newsList;
        }


        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(viewHolder.getAdapterPosition());
                    NewsActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            News news= mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    public List<News> getNewsList(){
        List<News> newsList = new ArrayList<>();
        for (int i=1;i<=50;i++){
            News news = new News();
            if (i==1){
                news.setTitle("Xi Jinping Holds Talks with Kim Jong-un, Chairman of the Workers' Party of Korea");
                news.setContent("Xinhua News Agency, Beijing, June 19th (Reporter Li Zhongfa)" +
                        "\n" + "On the 19th, General Secretary of the CPC Central Committee and President Xi Jinping held talks with Kim Jong-un, Chairman of the Workers’ Party of Korea and Chairman of the State Council, who arrived in Beijing on the same day. The leaders of the two countries exchanged views on the current development of China-DPRK relations and the situation on the Korean Peninsula. They unanimously expressed their wish to safeguard, consolidate and develop China-DPRK relations and jointly promote the good momentum facing the peace and stability of the Korean Peninsula. We will make positive contributions to maintaining peace, stability, prosperity and development in the world and in the region.\n" +
                        "\n" +
                        "　　Xi Jinping pointed out that we are pleased to see that Chairman Kim Jong-un and President Trump held an important meeting with President Trump in Singapore to reach a principled consensus on the realization of the denuclearization of the peninsula and the establishment of a lasting peace mechanism on the peninsula, and achieved positive results. The Chinese side highly praised this. Comrade Kim Jong-un’s comrades made a special trip to China, which reflected the great importance attached to the strategic communication between the two parties and the two countries. I highly value this.\n" +
                        "\n" +
                        "　　Xi Jinping emphasized that in less than three months, I met with the comrades of the chairmanship three times, pointed out the direction for the development of relations between the two parties and the two countries, and opened a new chapter in the development of China-DPRK relations. The Chinese party and government attach great importance to the friendly and cooperative relations between China and the DPRK. No matter how the international and regional situation changes, the Chinese Communist Party and the government’s firm stance of consolidating the development of China-DPRK relations will not change. The Chinese people’s friendship with the Korean people will not change. China’s support for socialist North Korea will not change. I would like to work with the comrades of the Chairman to continue to implement the important consensus reached by the two sides and promote the long-term, healthy and stable development of China-DPRK relations for the benefit of the two countries and their peoples.\n" +
                        "\n" +
                        "　　Xi Jinping pointed out that this year is the 40th anniversary of China's reform and opening up. Since the reform and opening up, the Chinese people have taken a foothold in their national conditions and looked at the world. They have been brave in self-revolution and self-innovation, and have explored a development path that is in line with their national conditions. We are pleased to see that North Korea has made a major decision to shift its focus to economic construction, and the development of North Korea’s socialist cause has entered a new historical stage. We support North Korea’s economic development, improvement of people’s livelihood, and support for North Korea’s development path in line with its national conditions.");

            }else if (i==2){
                news.setTitle("Xi Jinping Meets with Nepalese Prime Minister Ollie");
                news.setContent("新华社北京6月20日电（记者侯丽军）国家主席习近平20日在人民大会堂会见尼泊尔总理奥利。\n" +
                        "\n" +
                        "　　习近平指出，中国和尼泊尔是患难与共的友好邻邦。中尼建交以来，始终在和平共处五项原则基础上开展互利合作。中方赞赏尼方坚定奉行一个中国政策，将一如既往支持尼方维护国家独立、主权、领土完整，支持尼方自主选择适合本国国情的社会制度和发展道路，祝愿尼泊尔早日实现发展目标。\n" +
                        "\n" +
                        "　　习近平强调，当前中尼两国关系正面临新的发展机遇。双方要密切高层交往，加强战略沟通，继续坚持和平共处五项原则，尊重和照顾彼此核心利益和关切，巩固中尼关系政治基础，提升中尼关系政治站位。中方愿同尼方加强“一带一路”框架下基础设施互联互通、灾后重建、经贸投资等领域合作，构建全方位互利合作格局。双方要加强文化交流，打造中尼人文合作新亮点，筑牢中尼友好民意基础。要加强执法能力建设合作，共同打击跨国犯罪，维护好中尼共同安全。中尼要加强在国际重大问题上沟通协调。\n" +
                        "\n" +
                        "　　奥利表示，尼中友谊源远流长，尼中关系是不同制度、不同大小国家关系的典范。尼泊尔钦佩中国的发展成就，高度评价中国在国际事务中的重要积极作用和亲诚惠容的周边外交政策，感谢中国对尼泊尔国家转型发展的支持。尼泊尔坚定奉行一个中国政策，决不允许任何势力在尼泊尔领土上从事任何反华活动。尼方愿同中方拓展新形势下合作。尼方高度评价习主席提出的人类命运共同体主张，并愿积极参与“一带一路”建设。\n" +
                        "\n" +
                        "　　杨洁篪、王毅、何立峰等参加会见。");
            }
            else if (i==4){
                news.setTitle("Li Keqiang presided over the State Council executive meeting");
                news.setContent("新华社北京6月20日电 国务院总理李克强6月20日主持召开国务院常务会议，部署进一步缓解小微企业融资难融资贵，持续推动实体经济降成本；确定加快已在境外上市新药审批、落实抗癌药降价措施、强化短缺药供应保障；通过《医疗纠纷预防和处理条例（草案）》。\n" +
                        "\n" +
                        "　　会议指出，要坚持稳健中性的货币政策，保持流动性合理充裕和金融稳定运行，加强政策统筹协调，巩固经济稳中向好态势，增强市场信心，促进比较充分就业，保持经济运行在合理区间。会议确定了进一步缓解小微企业融资难融资贵的措施：一是增加支持小微企业和“三农”再贷款、再贴现额度，下调支小再贷款利率。完善考核机制，实现单户授信总额1000万元及以下小微企业贷款同比增速高于各项贷款增速，有贷款余额户数高于上年同期水平。二是从今年9月1日至2020年底，将符合条件的小微企业和个体工商户贷款利息收入免征增值税单户授信额度上限，由100万元提高到500万元。国家融资担保基金支持小微企业融资的担保金额占比不低于80%，其中支持单户授信500万元及以下小微企业贷款及个体工商户、小微企业主经营性贷款的担保金额占比不低于50%。三是禁止金融机构向小微企业贷款收取承诺费、资金管理费，减少融资附加费用。四是支持银行开拓小微企业市场，运用定向降准等货币政策工具，增强小微信贷供给能力，加快已签约债转股项目落地。鼓励未设立普惠金融事业部的银行增设社区、小微支行。五是将单户授信500万元及以下的小微企业贷款纳入中期借贷便利合格抵押品范围。\n" +
                        "\n" +
                        "　　为让群众早用上、用得起好药，解决好重点民生问题，会议确定，一是有序加快境外已上市新药在境内上市审批。对治疗罕见病的药品和防治严重危及生命疾病的部分药品简化上市要求，可提交境外取得的全部研究资料等直接申报上市，监管部门分别在3个月、6个月内审结。将进口化学药品上市前注册检验改为上市后监督抽样，不作为进口验放条件。二是督促推动抗癌药加快降价，让群众有更多获得感。各省（区、市）对医保目录内的抗癌药要开展专项招标采购。对医保目录外的独家抗癌药要抓紧推进医保准入谈判。开展国家药品集中采购试点，实现药价明显降低。三是加强全国短缺药品供应保障监测预警，建立短缺药品及原料药停产备案制度，加大储备力度，确保患者用药不断供。\n" +
                        "\n" +
                        "　　会议通过《医疗纠纷预防和处理条例（草案）》。草案突出医疗纠纷预防，规范医疗损害鉴定，要求充分发挥人民调解作用，明确了医疗纠纷处理途径和程序。\n" +
                        "\n" +
                        "　　会议还研究了其他事项。");
            }
            else if (i==5){
                news.setTitle("Xi Jinping accepts the new ambassador to China in the 13 countries");
                news.setContent("新华社北京6月20日电　国家主席习近平20日下午在人民大会堂接受13国新任驻华大使递交国书。\n" +
                        "\n" +
                        "　　盛夏时节，晴空万里，艳阳高照。人民大会堂北门外台阶上，礼兵分列红地毯两侧。号手吹响迎宾号角，新任驻华使节们先后抵达，沿着台阶拾级而上，进入北京厅。使节们依次向习近平呈递国书，习近平同他们亲切握手并合影留念。这13位新任驻华大使是：巴巴多斯驻华大使杰克曼、秘鲁驻华大使克萨达、安哥拉驻华大使内图、摩尔多瓦驻华大使杰利马莱、印度尼西亚驻华大使周浩黎、多米尼克驻华大使查尔斯、新西兰驻华大使傅恩莱、塞浦路斯驻华大使图马齐斯、特立尼达和多巴哥驻华大使西丹辛格、摩洛哥驻华大使梅库阿尔、苏丹驻华大使沙维尔、黑山驻华大使帕约维奇、圣马力诺驻华大使萨利齐奥尼。\n" +
                        "\n" +
                        "　　习近平欢迎各国使节来华履新，请他们转达对各有关国家领导人和人民的诚挚问候和美好祝愿，强调中国高度重视发展同各国关系，愿进一步增进互信、合作与交往，推动双边关系不断迈上新台阶，更好造福中国和各国人民。希望使节们为此作出积极贡献。中国政府将为使节们履职提供便利和支持。\n" +
                        "\n" +
                        "　　使节们转达了各自国家领导人对习近平的亲切问候，表示各国高度重视发展对华关系，珍视人民间友谊，希望积极参与“一带一路”建设，深化双方互利合作。使节们对出使中国深感荣幸，表示愿致力于增进各自国家同中国间相互了解和友谊、促进两国各领域交流合作。\n" +
                        "\n" +
                        "　　国务委员兼外交部长王毅参加上述活动。\n" +
                        "\n");
            }
            else if (i==6){
                news.setTitle("Together, share the \"World Cup\"!");
                news.setContent("新华社北京6月15日电 题：一起来，共享“世界杯”！\n" +
                        "\n" +
                        "　　辛识平\n" +
                        "\n" +
                        "　　世界杯来了！14日晚，全世界球迷的目光聚焦莫斯科卢日尼基体育场。时间不长却富有创意的开幕式表演过后，来自太空的揭幕战用球开始在绿茵场上飞驰。在现场近8万名观众的助威声中，东道主俄罗斯队5：0大胜沙特阿拉伯队，取得开门红。当俄罗斯风情与足球碰撞出绚丽的火花，四年一度的世界足球盛会再次点燃亿万球迷的激情。\n" +
                        "\n" +
                        "　　俄罗斯世界杯开场哨响的那一刻，一段火热的时光奔涌而来。32支队伍群雄逐鹿，64个回合针锋相对，数十亿观众的欢呼呐喊……未来一个月里，足球的“魅力风暴”将一如既往，席卷我们这个星球的各个角落，带给人们或狂喜、感动，或遗憾、伤感……这是各国健儿的竞技场，这是力与美的奇幻世界，这就是足球！\n" +
                        "\n" +
                        "　　对中国球迷而言，世界杯同样是盛大的节日。无论是身边的同事朋友，还是朋友圈里，一时间，“世界杯”成为热门话题。有人定下闹钟买好啤酒只等大戏开场，有人谋划“踢球＋看球”的运动“套餐”，也有人一边看球一边品味人生……在这个炎热的夏天，世界杯不仅是赛事，更是一个社交的平台、一种情感的寄托。\n" +
                        "\n" +
                        "　　脚下有足球，赛场有世界。当我们为中国男足再次缺席这一足球盛宴而遗憾时，有一支别样的“中国队”，已奔赴世界杯赛场。在俄罗斯，你会发现，球迷们手中的吉祥物、纪念币，不少球员们身上的球衣、脚下的足球，几乎都是“中国制造”。本届世界杯，四家中国品牌占据了顶级和二级赞助商的三分之一。从当年初级的周边代工，到如今的深度合作，世界杯上的中国企业的“进阶”历程，就是中国发展的一个缩影。当然，不可忘记的还有中国数量庞大、热情高涨的球迷。据统计，超过4万名中国球迷购买了俄罗斯世界杯的门票。\n" +
                        "\n" +
                        "　　无处不在的中国元素，展现着中国与世界第一运动的紧密联系，也引发人们对于足球事业发展振兴的思考。今天，中国足球正处于难得的好时代。近年来，足球改革不断深化，体制机制弊端正被破除，基础设施建设日趋完善，青少年足球蓬勃兴起，全社会足球文化正在生长……足球场上，或许可以一蹴而就锁定胜局，但一个国家足球水平的提高，不可能一步到位。越来越多的人们意识到，足球事业的发展，需要久久为功，需要齐心协力，通过扎扎实实的努力，一步一个脚印向着梦想迈进。\n" +
                        "\n" +
                        "　　有激情，也有期待，有热情，也有冷静。世界杯是丰富多彩的，更是不可错过的。让我们一起感受足球运动的精彩与乐趣，共同享受这段难忘的时光。");
            }else if (i==3){
                news.setTitle("To the ills of \"long delays\"");
                news.setContent("新华社北京６月１３日电 题：向“久拖不办”的顽疾开刀\n" +
                        "\n" +
                        "　　辛识平\n" +
                        "\n" +
                        "　　办张房产证要多久？证明自己身份有多少“奇葩”式规定？近期的一些新闻提出了值得深思的问题。\n" +
                        "\n" +
                        "　　“星星还是那个星星，问题还是那个问题”，群众反映强烈的办事难、办事慢问题，常常集中体现在“久拖不办”上。有的部门和干部，一事当前，或念起“拖字诀”，满口“正在研究”“尽快处理”，实际上按兵不动，没有下文；或奉行“推字诀”，动辄以“不符合规定”“没有上级指示”等为由头，堂而皇之当起甩手掌柜。无论是“十年不办”还是“九年才改”，种种不良现象的背后，实则是不担当、不作为的懒政怠政。\n" +
                        "\n" +
                        "　　“能办不办，该办不办；拖着不干，等等看看”，类似问题并不少见。老百姓其实心里有本账：“不是不能，而是不为。”很多时候，让人恼火的，不是事情本身有多难办，而是相关人员怕惹麻烦“不想办”、怕担责任“不愿干”。还有“新官不理旧账”，怕接“烫手山芋”，硬生生把事情架起来，让问题和矛盾不断积累。\n" +
                        "\n" +
                        "　　群众利益无小事，一枝一叶总关情。对百姓而言，身边每一件看似琐碎的小事，很多是关系切身利益的大事，有的还是急事、难事。倘若干部不想担当、不愿作为，在“推、绕、拖”中混日子，必然损害群众利益、疏远干群关系，必然损伤党和政府的形象和公信力。“久拖不办”的顽疾，病根在于宗旨意识淡化，对群众缺乏感情，对民生疾苦漠不关心，骨子里还是那种高高在上、脱离群众的官僚主义。\n" +
                        "\n" +
                        "　　习近平总书记一再要求各级干部，始终把人民放在心中最高位置。“最高位置”不是挂在嘴上的，而应落实于行动，以“最实举措”来体现和证明。怎么做？就是坚持把人民群众的小事当作自己的大事，从人民群众关心的事情做起，从让人民群众满意的事情做起，多谋民生之利，多解民生之忧。看一个干部有没有为民情怀、有没有担当精神，有一个直观而有说服力的“试金石”，就是看其面对群众的操心事、烦心事，会不会挑起担子、撸起袖子，能不能敢于担当、勇于作为。\n" +
                        "\n" +
                        "　　根治“久拖不办”的顽疾，还得靠制度。不久前，中办印发的《关于进一步激励广大干部新时代新担当新作为的意见》，鲜明树立重实干重实绩的用人导向，既为敢于担当的干部撑腰鼓劲，又使慢作为、不作为、乱作为的干部受到警醒和惩戒，从而通过制度实现“优者上、庸者下、劣者汰”。让担当作为成为自觉，让“马上就办”成为习惯，让监督问责的压力激发动力，各级干部就能在其位、谋其政、干其事、求其效，实实在在为群众服好务、尽好责。");

            }else {
                news.setTitle("Xi Jinping's Shanghe \"Heart\" Power");
                news.setContent("新华社北京５月２８日电  题：习近平的上合“心”动力\n" +
                        "\n" +
                        "　　辛识平\n" +
                        "\n" +
                        "　　２０１８中国主场外交将在“帆船之都”青岛迎来又一个高光时刻。外交部２８日宣布，上海合作组织成员国元首理事会第十八次会议将于６月９日至１０日在青岛举行，习近平主席将主持会议并举行相关活动。从黄浦江畔到黄海之滨，从６个成员国到８个成员国，上合迎来扩员后的首次元首峰会。一个体量更大、音量更高的上合组织即将开启新的远航。\n" +
                        "\n" +
                        "　　１７年乘风破浪，上合组织在时代的风云变幻中开辟了一条相互尊重、公平正义、合作共赢的宽广航道。５年来，上合组织乘势而上，同行者越来越多，驱动力越来越强；从引领方向到深耕合作再到携手世界，习主席出席历次元首峰会，为上合发展注入源源不断的动力。进入“青岛时间”，习主席集体会见上合组织成员国国防部长、外长、安全秘书，观时代潮流、谈安全形势、话合作机遇、谋命运与共，将心比心、以心换心的坦诚务实，引领和推动上合组织行稳致远。\n" +
                        "\n" +
                        "　　风雨兼程，不忘初心。互信、互利、平等、协商、尊重多样文明、谋求共同发展的“上海精神”是上合组织的“初心”。从最初的６个创始成员国，到如今的１８个成员国、观察员国与对话伙伴，大家在对和平与繁荣的追求中找到了共同语言，也找到合作的最大公约数，给世界带来合作共赢新的可能，为致力于睦邻友好和共同繁荣的国家提供了有益借鉴。\n" +
                        "\n" +
                        "　　合作共赢，重在同心。上合之“合”，始于安全需要，更兴于经贸、人文合作。“务实合作是上海合作组织发展的物质基础和原动力”。严厉打击“东突”等“三股势力”、举行“和平使命”系列军事演习，大大提升了各国人民的安全感；从“实现了几代人梦想”的乌兹别克斯坦安格连—帕普铁路隧道工程，到满载货物、穿梭往返的中亚班列，“一带一路”建设为老百姓带来实实在在的获得感；“九个美妙乐章”系列音乐会以“琴”会友，上合组织国家电影节即将揭幕，文化纽带拉近彼此距离，更使民心相通。让安全、经济、人文三个轮子一齐转动，上合之“合”才能愈加稳固、更有底气。\n" +
                        "\n" +
                        "　　大道之行，秉持公心。“扩员之后，上海合作组织安全合作潜力更大，肩负的责任更重”。这一责任，既是为地区谋发展的使命，也是“为世界谋大同”的担当。从恐怖主义到气候变化，从阿富汗问题到叙利亚问题，从伊朗核问题到乌克兰危机……关注地区热点、应对各类挑战、完善全球治理，正成为上合担起的重要责任。作为“重量级”的对话平台，上合组织密切协调立场，发出响亮“上合声音”，必将为构建人类命运共同体注入强劲的“上合力量”。\n" +
                        "\n" +
                        "　　看海鸥翔集，望帆影点点，闻花香满城。相聚孟夏的青岛，上合组织迎来新的发展机遇。“上海合作组织青岛峰会必将成功”，这是习主席的庄严承诺，也是地区和世界的共同期待。");
            }
            newsList.add(news);
        }
        return newsList;
    }

}
