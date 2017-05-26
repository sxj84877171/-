package com.soarsky.car.ui.ticketed;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.TicketDao;
import com.soarsky.car.uitl.LogUtil;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：罚单数据库上传<br>
 * 该类为<br>
 */

public class TicketDb {
    /**
     * org.greenrobot.greendao.AbstractDaoSession
     */
    private DaoSession daoSession;
    private Context  context;
    private RxDao<Ticket, Void> ticketDao;
    private RxQuery<Ticket> ticketQuery;
    public TicketDb(Context context){
        this.context=context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }



        private static TicketDb single=null;
        //静态工厂方法
        public static TicketDb getInstance(Context context) {
             if (single == null) {
                 single = new TicketDb(context);
             }
            return single;
        }


    public RxDao<Ticket,Void> getTicketDao(){
        if(ticketDao==null){
            ticketDao=daoSession.getTicketDao().rx();
        }
        return ticketDao;
    }


    public RxQuery<Ticket>getTicketQuery(){
        if(ticketQuery==null){
            ticketQuery=daoSession.getTicketDao().queryBuilder().rx();
        }
        return ticketQuery;
    }

    /**
     * 保存罚单数据 先判断该罚单是否存在 如果存在就不保存
     * @param ticket
     * @return
     */
    public void inserData(Ticket ticket){

        LogUtil.i("inserData");

        if(!hasTicket(ticket.getId(),ticket.getCarnum())) {
           getTicketDao().insert(ticket).subscribe(new Subscriber<Ticket>() {
                @Override
                public void onCompleted() {
                    LogUtil.i("onCompleted");
                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.i("onError"+e.getStackTrace().toString());
                }

                @Override
                public void onNext(Ticket ticket) {
                    LogUtil.i("onNext"+ticket.toJson());
                }
            });
        }
    }

    /**
     * 查询未同步到服务器的罚单数据
     */
    public List<Ticket> getNuService(){
//        return getTicketDao().getDao().queryBuilder().list();

        return getTicketDao().getDao().queryBuilder().where(TicketDao.Properties.ServiceStatus.eq(1)).list();
    }


    /**
     * 判断罚单是否存在
     * @param Id
     * @return
     */
    public  boolean hasTicket(String Id,String carNum){
        List<Ticket> tickets=getTicketDao().getDao().queryBuilder().where(TicketDao.Properties.Id.eq(Id),TicketDao.Properties.Carnum.eq(carNum)).list();
        if(tickets!=null&&tickets.size()>0){
            return  true;
        }
        return  false;
    }



    /**
     * 查询未同步到终端的罚单数据
     */
    public List<Ticket> getNuTerminal(){

        return getTicketDao().getDao().queryBuilder().where(TicketDao.Properties.ServiceStatus.eq(2),TicketDao.Properties.TerminalStatus.eq(1)).list();
    }


    /**
     * 修改罚单的终端状态
     * @param id
     */
    public  void  updateData(int id){
        getTicketDao().getDao().getDatabase().execSQL("update  TICKET set TERMINAL_STATUS =2  where ID = '"+id+"'");
    }


    /**
     * 修改罚单的服务端状态
     * @param id
     */
    public  void  updateServiceStatus(int id){
        getTicketDao().getDao().getDatabase().execSQL("update  TICKET set SERVICE_STATUS =2  where ID = '"+id+"'");
    }


    /**
     * 更新罚单状态
     */
    public void updateData(Ticket ticket){
        getTicketDao().update(ticket).subscribe(new Subscriber<Ticket>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.i("onNext"+ e.getStackTrace().toString());
            }

            @Override
            public void onNext(Ticket ticket) {
                LogUtil.i("onNext"+ticket.toJson());
            }
        });
    }


    /**
     * 删除所有数据
     */
    public  void  deleteAll(){
        getTicketDao().getDao().deleteAll();
    }
}
