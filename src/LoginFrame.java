import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private JButton btnChoseFile;
	private JButton btnOutFile;

	public LoginFrame() {
		btnChoseFile = new JButton("点击");
		btnChoseFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();// 这个就是你选择的文件的路径
					btnChoseFile.setText(filePath);
				}
			}
		});
		btnOutFile = new JButton("点击");
		btnOutFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();// 这个就是你选择的文件夹的路径
					btnOutFile.setText(filePath);
				}
			}
		});
		JPanel p1 = new JPanel();
		p1.add(new JLabel("选择文件:"));
		p1.add(btnChoseFile);
		p1.add(new JLabel("输出路径:"));
		p1.add(btnOutFile);

		add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel();
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnChoseFile.getText().equals("点击")||btnOutFile.getText().equals("点击")) {
					JOptionPane.showMessageDialog(LoginFrame.this, "请选择文件！");
					return;
				}
				File imageFile = new File(btnChoseFile.getText());
				File out = new File(btnOutFile.getText());
				splitImage(imageFile, out, 256, 256);
				JOptionPane.showMessageDialog(LoginFrame.this, "完成！");
			}
		});
		p2.add(button);
		button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		p2.add(button);

		add(p2, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(805, 410);
		// JFrame打开后居中。
		setLocationRelativeTo(getOwner());
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

	public static void splitImage(File imageFile, File outDir, int split_width,
			int split_height) {
		try {
			BufferedImage image = ImageIO.read(imageFile);
			// save to file "startx,starty,sizex,sizey,level.png"
			final int width = image.getWidth();
			final int height = image.getHeight();
			int sizex = split_width;
			int sizey = split_height;
			int level = 0;
			for (int startx = 0; startx < width; startx += split_width) {
				for (int starty = 0; starty < height; starty += split_height) {
					if (width - startx < split_width) {
						sizex = width - startx;
					} else {
						sizex = split_width;
					}
					if (height - starty < split_height) {
						sizey = height - starty;
					} else {
						sizey = split_height;
					}
					BufferedImage sub = image.getSubimage(startx, starty,
							sizex, sizey);
					String outName = startx + "," + starty + "," + sizex + ","
							+ sizey + "," + level + ".png";
					File out = new File(outDir, outName);
					ImageIO.write(sub, "png", out);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}