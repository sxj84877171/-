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
 * Created by 何明辉 on 2016/11/30.
 * 罚单数据库操作
 */

public class TicketDb {
    private DaoSession daoSession;
    private Context  context;
    private RxDao<Ticket, Long> ticketDao;
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


    public RxDao<Ticket,Long> getTicketDao(){
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

        if(!hasTicket(ticket.getTicketNo())) {
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

        return getTicketDao().getDao().queryBuilder().where(TicketDao.Properties.ServiceStatus.eq(1)).list();
    }


    /**
     * 判断罚单是否存在
     * @param ticketNo
     * @return
     */
    public  boolean hasTicket(String ticketNo){
        List<Ticket> tickets=getTicketDao().getDao().queryBuilder().where(TicketDao.Properties.TicketNo.eq(ticketNo)).list();
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

}
