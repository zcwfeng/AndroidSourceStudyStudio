package com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen.view;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.zcwfeng.sourcestudy.androidsourcestudystudio.R;

import static com.zcwfeng.sourcestudy.androidsourcestudystudio.shotscreen.util.Calc.getPointDis;


public class ShotView extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
	private static final int RAD = 5;
	private Paint paint;
	private SurfaceHolder sh;
	private boolean isRunning = true;
	private Canvas canvas;
	private Bitmap bitmap = null;
	private Rect rect;
	private Point[] points = new Point[8];
	
	private Point startPoint;
	private Point endPoint;
	private Path path;
	private PopupWindow popupWindow;
	private Bitmap saveBitmap;
	
	private int drawState = 0;
	private int index = -1;
	
	public void setIsRunning( boolean b )
	{
		// TODO Auto-generated method stub
		this.isRunning = b;
	}
	
	public void setBitmap( Bitmap bitmap )
	{
		this.bitmap = bitmap;
	}

	public Bitmap getBitmap( )
	{
		return bitmap;
	}

	public ShotView( Context context )
	{
		super( context );
		// TODO Auto-generated constructor stub
		init( );
	}
	
	public ShotView( Context context, AttributeSet attrs )
	{
		super( context, attrs );
		// TODO Auto-generated constructor stub
		init( );
	}
	
	private void init( )
	{
		//setZOrderOnTop(true);
		paint = new Paint( );
		sh = this.getHolder( );
		sh.addCallback( this );
		//sh.setFormat(PixelFormat.TRANSLUCENT);
		rect = new Rect( );
		startPoint = new Point( );
		endPoint = new Point( );
		path = new Path( );
		this.setFocusable( true );
	}
	
	@Override
	public void surfaceCreated( SurfaceHolder holder )
	{
		// TODO Auto-generated method stub
		new Thread( this ).start( );
		
	}
	
	@Override
	public void surfaceChanged( SurfaceHolder holder, int format, int width, int height )
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceDestroyed( SurfaceHolder holder )
	{
		// TODO Auto-generated method stub
		isRunning = false;	
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onTouchEvent( MotionEvent event )
	{
		switch ( event.getAction( ) )
		{
		
			case MotionEvent.ACTION_DOWN:
				System.out.println( " down " );
				Point downPoint = new Point( ( int ) event.getX( ), ( int ) event.getY( ) );
				index = checkDownpoint( downPoint );
				if ( drawState == 1 && index != -1 )
				{
					if ( index == 0 || index == 2 || index == 5 || index == 7 )
					{
						startPoint = points[7 - index];
					}
					else if(index == 1 || index == 3 || index == 4 || index == 6)
					{
						drawState = 2;
					}
					else if ( index == 8 )
					{ 
						drawState = 3;
						startPoint = downPoint;
					}
				}
				else
				{
					startPoint = downPoint;
				}
				
				path.moveTo( event.getX( ), event.getY( ) );
				break;
			
			case MotionEvent.ACTION_MOVE:
				
				path.lineTo( event.getX( ), event.getY( ) );
				endPoint.x = ( int ) event.getX( );
				endPoint.y = ( int ) event.getY( );
				int left = 0;
				int top = 0;
				int right = 0;
				int bottom = 0;
				
				if ( drawState == 0 || drawState == 1 )
				{
					left = Math.min( startPoint.x, endPoint.x );
					top = Math.min( startPoint.y, endPoint.y );
					right = Math.max( startPoint.x, endPoint.x );
					bottom = Math.max( startPoint.y, endPoint.y );
					rect.set( left, top, right, bottom );
				}
				else if ( drawState == 2 )
				{
					if ( index == 1 || index == 6 )
					{
						top = points[index - 1].y;
						bottom = points[index + 1].y;
						left = Math.min( points[7 - index].x, endPoint.x );
						right = Math.max( points[7 - index].x, endPoint.x );
					}
					else if ( index == 3 || index == 4 )
					{
						left = points[index - 2].x;
						right = points[index + 2].x;
						top = Math.min( points[7 - index].y, endPoint.y );
						bottom = Math.max(  points[7 - index].y, endPoint.y );
					}
					rect.set( left, top, right, bottom );
				}
				else if (drawState == 3)
				{
					int dx = endPoint.x - startPoint.x;
					int dy = endPoint.y - startPoint.y;
					if ( rect.left > 0 && rect.right < getWidth( ) && rect.top > 0 && rect.bottom < getHeight( ))
					{
						if ( !(rect.left + dx > 0 && rect.right + dx < getWidth( ) && rect.top + dy > 0 && rect.bottom + dy < getHeight( )) )
						{
							break;
						}
						rect.offset( dx,  dy);
						startPoint.x = endPoint.x;
						startPoint.y = endPoint.y;
					}
					System.out.println(rect);
				}
				
			
				break;
			
			case MotionEvent.ACTION_UP:
				
				Log.i( "MotionEvent.ACTION_UP", event.getX( ) + " " + event.getY( ) );
				if ( popupWindow != null )
				{
					popupWindow.dismiss( );
					popupWindow = null;
				}
				
				drawState = 1;
				View view = LayoutInflater.from( getContext( ) ).inflate( R.layout.popwindow_confirm, null );
				popupWindow = new PopupWindow( view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
				popupWindow.setOutsideTouchable( true );
				popupWindow.showAtLocation( this, Gravity.LEFT | Gravity.TOP, rect.right, rect.bottom );
				setPopListener( view );
				break;
		}
		
		return true;
	}
	
	private int checkDownpoint( Point downPoint )
	{
		// TODO Auto-generated method stub
		
		points[0] = new Point( rect.left, rect.top );
		points[1] = new Point( rect.left, ( rect.top + rect.bottom ) / 2 );
		points[2] = new Point( rect.left, rect.bottom );
		points[3] = new Point( ( rect.left + rect.right ) / 2, rect.top );
		points[4] = new Point( ( rect.left + rect.right ) / 2, rect.bottom );
		points[5] = new Point( rect.right, rect.top );
		points[6] = new Point( rect.right, ( rect.top + rect.bottom ) / 2 );
		points[7] = new Point( rect.right, rect.bottom );
		
		for ( int i = 0; i < points.length; i++ )
		{
			if ( getPointDis( points[i], downPoint ) < 3 * RAD )
			{
				return i;
			}
		}
		
		if ( downPoint.x > rect.left && downPoint.x < rect.right && downPoint.y > rect.top && downPoint.y < rect.bottom)
		{
			return 8;
		}
		
		return -1;
	}
	
	private void setPopListener( View view )
	{
		// TODO Auto-generated method stub
		popupWindow.getContentView( ).setOnTouchListener( new OnTouchListener( )
		{
			
			@Override
			public boolean onTouch( View v, MotionEvent event )
			{
				if ( event.getAction( ) == MotionEvent.ACTION_OUTSIDE )
				{
					popupWindow.dismiss( );
					popupWindow = null;
				}
				return true;
			}
		} );
		
		view.findViewById( R.id.cancle_ll ).setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText( getContext( ), "cancel", Toast.LENGTH_SHORT ).show( );
				rect.setEmpty( );
				invalidate( );
				if ( popupWindow != null )
				{
					popupWindow.dismiss( );
					popupWindow = null;
				}
			}
		} );
		
		view.findViewById( R.id.back_ll ).setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText( getContext( ), "back", Toast.LENGTH_SHORT ).show( );
				rect.setEmpty( );
				invalidate( );
				if ( popupWindow != null )
				{
					popupWindow.dismiss( );
					popupWindow = null;
				}
				isRunning = false;
				setVisibility( View.INVISIBLE );
			}
		} );
		
		view.findViewById( R.id.ok_ll ).setOnClickListener( new OnClickListener( )
		{
			
			@Override
			public void onClick( View v )
			{
				// TODO Auto-generated method stub
				Toast.makeText( getContext( ), "ok", Toast.LENGTH_SHORT ).show( );
				
				saveBitmap = Bitmap.createBitmap( ShotView.this.bitmap, rect.left, rect.top , rect.width( ), rect.height( ) );
				
				ImageView imgView = new ImageView( getContext());
				imgView.setImageBitmap( saveBitmap );
				new AlertDialog.Builder(getContext( ))  
				.setTitle("截图")
				.setView(imgView)  
				.setPositiveButton("ok", null)
				.show();  
				
			}
		} );
	}
	
	@Override
	public void run( )
	{
		// TODO Auto-generated method stub
		while ( isRunning )
		{
			drawView( );
			try
			{
				Thread.sleep( 100 );
			}
			catch ( InterruptedException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
		}
	}
	
	private void drawView( )
	{
		// TODO Auto-generated method stub
		try
		{
			if ( sh != null )
			{
				canvas = sh.lockCanvas( );
				canvas.setDrawFilter( new PaintFlagsDrawFilter( 0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG ) );
				canvas.drawBitmap( bitmap, 0,0, null );
				paint.setStyle( Style.STROKE );
				// canvas.drawPath( path, paint );
				paint.setColor( Color.BLUE );
				paint.setStrokeWidth( 2 );
				canvas.drawRect( rect, paint );
				
				paint.setStyle( Style.FILL );
				paint.setColor( Color.GREEN );
				//paint.setStrokeWidth( 3 );
				
				canvas.drawCircle( rect.left, rect.top, RAD, paint );//
				canvas.drawCircle( rect.left, ( rect.top + rect.bottom ) / 2, RAD, paint );
				canvas.drawCircle( rect.left, rect.bottom, RAD, paint );
				canvas.drawCircle( ( rect.left + rect.right ) / 2, rect.top, RAD, paint );
				canvas.drawCircle( ( rect.left + rect.right ) / 2, rect.bottom, RAD, paint );
				canvas.drawCircle( rect.right, rect.top, RAD, paint );
				canvas.drawCircle( rect.right, ( rect.top + rect.bottom ) / 2, RAD, paint );
				canvas.drawCircle( rect.right, rect.bottom, RAD, paint );
				
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		finally
		{
			if ( canvas != null )
				sh.unlockCanvasAndPost( canvas );
		}
		
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            isRunning = false;
        }
        return super.onKeyDown(keyCode, event);
    }


}
